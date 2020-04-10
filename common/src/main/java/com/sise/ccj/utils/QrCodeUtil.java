package com.sise.ccj.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class QrCodeUtil {
    private static QRCodeWriter codeWriter = new QRCodeWriter();
    private QrCodeUtil(){}
    public static void createQrCode(String content,int width, int height, HttpServletResponse response) throws WriterException, IOException {
        BitMatrix bit = codeWriter.encode(new String(content.getBytes(), StandardCharsets.UTF_8), BarcodeFormat.QR_CODE, width, height);
        MatrixToImageWriter.writeToStream(bit, "PNG", response.getOutputStream());
    }

    public static void createQrCode(String content,int width, int height, String path) throws WriterException, IOException {
        File file = new File(path);
        if (!file.exists()){
            file.createNewFile();
        }
        createQrCode(content,width, height, file);
    }

    public static void createQrCode(String content,int width, int height, File file) throws WriterException, IOException {
        BitMatrix bit = codeWriter.encode(new String(content.getBytes(), StandardCharsets.UTF_8), BarcodeFormat.QR_CODE, width, height);
        if (!file.exists()){
            file.createNewFile();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        MatrixToImageWriter.writeToStream(bit, "PNG", fileOutputStream);
    }
}
