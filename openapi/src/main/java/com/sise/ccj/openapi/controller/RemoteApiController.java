package com.sise.ccj.openapi.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sise.ccj.constant.CommonConstant;
import com.sise.ccj.exception.ServerException;
import com.sise.ccj.mapper.OrderMapper;
import com.sise.ccj.mapper.UserMapper;
import com.sise.ccj.openapi.config.OpenApiConfig;
import com.sise.ccj.pojo.admin.UserPO;
import com.sise.ccj.pojo.common.OrderPO;
import com.sise.ccj.service.OrderService;
import com.sise.ccj.utils.QrCodeUtil;
import com.sise.ccj.utils.RSAEncrypt;
import com.sise.ccj.vo.HttpBody;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Locale;
import java.util.TimeZone;

import static org.apache.poi.ss.usermodel.Workbook.PICTURE_TYPE_PNG;

@RestController
@RequestMapping("/api")
public class RemoteApiController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OpenApiConfig openApiConfig;

    @Autowired
    private OrderService orderService;


    @PostMapping("/{token}/createOrder")
    public HttpBody createOrder(@PathVariable String token, @RequestBody OrderPO param) {
        UserPO userPO = userMapper.queryUserPoByToken(token);
        if (userPO == null) {
            throw new ServerException("用户不存在");
        }
        String tableSpace = CommonConstant.TABLE_SPACE.replace(CommonConstant.TABLE_SPACE_ID, userPO.getId() + "");
        param.setYId(userPO.getId());
        param.setOpenId(userPO.getToken());
        orderService.insertUpdate(param, tableSpace);
        return HttpBody.SUCCESS;
    }

    @GetMapping("/{token}/checkOrder")
    public HttpBody checkOrder(@PathVariable String token, @RequestParam("orderSn")String orderSn){
        UserPO userPO = userMapper.queryUserPoByToken(token);
        String tableSpace = CommonConstant.TABLE_SPACE.replace(CommonConstant.TABLE_SPACE_ID, userPO.getId() + "");
        OrderPO orderPO = orderMapper.queryOrderByOrderSn(tableSpace, orderSn);
        orderPO.setStatus(4);
        orderPO.setDbPrefix(tableSpace);
        orderMapper.insertUpdate(orderPO);
        return HttpBody.SUCCESS;
    }

    @GetMapping("/{token}/printOrder")
    public void printOrder(@PathVariable String token, @RequestParam("orderSn")String orderSn, HttpServletResponse response) throws Exception {
        UserPO userPO = userMapper.queryUserPoByToken(token);
        if (userPO == null) {
            throw new ServerException("token 有误");
        }
        String tableSpace = CommonConstant.TABLE_SPACE.replace(CommonConstant.TABLE_SPACE_ID, userPO.getId() + "");
        String str = RSAEncrypt.decrypt(orderSn, openApiConfig.getRsaPrivate());
        OrderPO orderPO = orderMapper.queryOrderByOrderSn(tableSpace, str);
        orderPO.setDbPrefix(tableSpace);
        if (orderPO.getStatus() != 1){
            throw new ServerException("订单状态有误");
        }
        JSONArray info = JSON.parseArray(orderPO.getInfo());
        HSSFWorkbook xssfWorkbook = new HSSFWorkbook();
        HSSFSheet sheet = xssfWorkbook.createSheet();
        Row row1 = sheet.createRow(1);
        Row row2 = sheet.createRow(2);
        Row row3 = sheet.createRow(3);
        Row row4 = sheet.createRow(4);
        Row row5 = sheet.createRow(5);
        Row row6 = sheet.createRow(6);
        for (int i = 0; i< info.size(); i++){
            sheet.setColumnWidth(i,10240);
            JSONObject row = info.getJSONObject(i);
            row1.createCell(i).setCellValue("在线电影订票");
            row2.createCell(i).setCellValue("订单号:"+orderPO.getOrderSn());
            row3.createCell(i).setCellValue("电影名称:"+row.getString("movieName"));
            row4.createCell(i).setCellValue("场地:"+row.getString("address")+"/"+row.getString("spaceName"));
            row5.createCell(i).setCellValue("座位:"+row.getString("location"));
            row6.createCell(i).setCellValue("上映时间:"+row.getString("upTime"));
        }
        response.addHeader("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8");
        response.addHeader("Pragma", "no-cache");
        response.addHeader("Cache-Control", "no-cache");
        response.addHeader("Expires", new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US).format(0));
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("电影票文件.xlsx", "UTF8"));
        try{
            xssfWorkbook.write(response.getOutputStream());
            orderPO.setStatus(2);
            orderMapper.insertUpdate(orderPO);
        }finally {
        }
    }

    @GetMapping("/{token}/getKey")
    public HttpBody getPublicKey(@PathVariable String token) {
        return HttpBody.getSucInstance(openApiConfig.getRsaPublic());
    }
}
