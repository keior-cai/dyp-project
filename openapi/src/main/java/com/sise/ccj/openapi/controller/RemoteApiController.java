package com.sise.ccj.openapi.controller;

import com.lowagie.text.pdf.BaseFont;
import com.sise.ccj.constant.CommonConstant;
import com.sise.ccj.exception.ServerException;
import com.sise.ccj.mapper.OrderMapper;
import com.sise.ccj.mapper.UserMapper;
import com.sise.ccj.openapi.config.OpenApiConfig;
import com.sise.ccj.pojo.admin.UserPO;
import com.sise.ccj.pojo.common.OrderPO;
import com.sise.ccj.service.OrderService;
import com.sise.ccj.utils.RSAEncrypt;
import com.sise.ccj.vo.HttpBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

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


    @GetMapping("/{token}/printOrder")
    public HttpBody printOrder(@PathVariable String token) throws Exception {
        String key = RSAEncrypt.decrypt(token, openApiConfig.getRsaPrivate());
        String[] keys = key.split("-");
        String yId = keys[0];
        String orderSn = keys[1];
        // 打印订单
        return HttpBody.SUCCESS;
    }

    @GetMapping("/{token}/getKey")
    public HttpBody getPublicKey(@PathVariable String token) {
        return HttpBody.getSucInstance(openApiConfig.getRsaPublic());
    }

    public static void createTextPDF(String content) {
        String htmlString = content.replaceAll("\"", "'").replaceAll("<style>", "<style>body{font-family:SimSun;font-size:14px;} @page{size : 200mm  300 mm;}");    //注意这里为啥要写这个，主要是替换成这样的字体，如果不设置中文有可能显示不出来。

        OutputStream os = null;    //生成PDF文件的路径
        try {
            os = new FileOutputStream("aa.pdf");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ITextRenderer renderer = new ITextRenderer();
        ITextFontResolver font = renderer.getFontResolver();
        try {
            font.addFont("C:/WINDOWS/Fonts/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);//添加中文识别，这里是设置的宋体，Linux下要换成对应的字体
        } catch (com.lowagie.text.DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        renderer.setDocumentFromString(htmlString.toString());
        renderer.layout();
        try {
            renderer.createPDF(os);
        } catch (com.lowagie.text.DocumentException e) {
            e.printStackTrace();
        }
        renderer.finishPDF();
    }
}
