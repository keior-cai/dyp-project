package com.sise.ccj.utils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class QrCodeUtil {
    private static QRCodeWriter codeWriter = new QRCodeWriter();
    private QrCodeUtil(){}
    public static void createQrCode(String content,int width, int height, HttpServletResponse response) throws WriterException, IOException {
        BitMatrix bit = codeWriter.encode(new String(content.getBytes(), StandardCharsets.UTF_8), BarcodeFormat.QR_CODE, width, height);
        MatrixToImageWriter.writeToStream(bit, "PNG", response.getOutputStream());
    }
}
