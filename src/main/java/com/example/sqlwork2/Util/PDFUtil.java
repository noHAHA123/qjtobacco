package com.example.sqlwork2.Util;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.util.Matrix;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@Slf4j
@Component
public class PDFUtil {

        public static void A() throws IOException {

            try (PDDocument doc = new PDDocument()) {

                //设置横向A4页面
                PDPage myPage = new PDPage(PDRectangle.A4);
                myPage.setRotation(90);
                doc.addPage(myPage);

                try (PDPageContentStream cont = new PDPageContentStream(doc, myPage, PDPageContentStream.AppendMode.OVERWRITE,true,true)) {

                    // 将坐标系旋转90度以适应横向页面
                    cont.transform(Matrix.getRotateInstance(Math.toRadians(90), 0, 0));
                    cont.transform(Matrix.getTranslateInstance(0, -PDRectangle.A4.getWidth()));

                    PDType0Font font = PDType0Font.load(doc, new File("C:\\Windows\\Fonts\\simfang.ttf"));
                    //cont.setFont(PDType0Font.load(doc,new File("C:\\Windows\\Fonts\\simfang.ttf")), 24);

                    // 设置字体和字体大小
                    cont.setFont(font, 18);

                    // 开始文本
                    cont.beginText();
                    cont.newLineAtOffset(600, 340);
                    String tail = "制表日期：";
                    cont.showText(tail);
                    cont.endText();


                    // 设置标题字体和大小
                    cont.setFont(font, 24);

                    String title = "烟叶收购(补贴)资金代付统计表";
                    float titleWidth = font.getStringWidth(title) / 1000 * 24;
                    float titleX = (PDRectangle.A4.getHeight() - titleWidth) / 2;
                    System.out.println((PDRectangle.A4.getWidth() - 50));
                    System.out.println(titleX);

                    // 开始文本
                    cont.beginText();
                    //cont.newLineAtOffset(300, 450);
                    cont.newLineAtOffset(titleX, PDRectangle.A4.getWidth() - 50);
//                    cont.showText("烟叶收购(补贴)资金代付统计表");
                    cont.showText(title);
                    cont.endText();



                    // 设置标题字体和大小
                    cont.setFont(font, 18);

                    String title1 = "(烟点付款/扣款明细表)";
                    float titleWidth1 = font.getStringWidth(title1) / 1000 * 24;
                    float titleX1 = (PDRectangle.A4.getHeight() - titleWidth1) / 2;

                    // 开始文本
                    cont.beginText();
                    //cont.newLineAtOffset(300, 450);
                    cont.newLineAtOffset(titleX1, PDRectangle.A4.getWidth() - 75);
//                    cont.showText("烟叶收购(补贴)资金代付统计表");
                    cont.showText(title1);
                    cont.endText();



                    // 绘制表格
                    float margin = 100; // 页面边距，表格左侧和右侧的边距
                    float yStart = PDRectangle.A4.getWidth() - 80; // 表格开始的垂直位置，距离页面顶部一定距离
                    float tableWidth = PDRectangle.A4.getHeight() - 2 * margin; // 表格的总宽度，减去左右边距
                    float yPosition = yStart; // 当前绘制位置的y坐标，初始化为表格开始位置
                    float rowHeight = 20f; // 每一行的高度
                    float cellMargin = 5f; // 单元格内的边距，用于文本和单元格边框之间的距离

                    // 总列数为6，保证所有列宽一致
                    int totalCols = 6;
                    float colWidth = tableWidth / totalCols; // 每列的宽度

                    // 第一部分：5行2列，第二列的宽度与6列部分的列宽一致
                    int cols1 = 2;

                    // 绘制第一部分表格：5行2列
                    for (int i = 0; i < 5; i++) { // 循环绘制5行
                        for (int j = 0; j < cols1; j++) { // 循环绘制每行中的每列
                            float colSpan = (j == 1) ? totalCols - 1 : 1; // 如果是第二列，跨度为总列数减一，否则为1
                            float xPosition = margin + j * colWidth; // 当前单元格的x坐标位置
                            float width = colWidth * colSpan; // 当前单元格的宽度，考虑跨度
                            cont.addRect(xPosition, yPosition - rowHeight, width, rowHeight); // 绘制矩形，即单元格
                        }
                        yPosition -= rowHeight; // 更新y坐标位置，移到下一行
                    }

                    // 绘制第二部分表格：3行6列
                    int cols2 = totalCols;
                    for (int i = 0; i < 3; i++) { // 循环绘制3行
                        for (int j = 0; j < cols2; j++) { // 循环绘制每行中的每列
                            float xPosition = margin + j * colWidth; // 当前单元格的x坐标位置
                            cont.addRect(xPosition, yPosition - rowHeight, colWidth, rowHeight); // 绘制矩形，即单元格
                        }
                        yPosition -= rowHeight; // 更新y坐标位置，移到下一行
                    }

                    cont.stroke(); // 绘制表格的边框

                    cont.setFont(font,12);

//                    // 填充表格内容
//                    yPosition = yStart - cellMargin - rowHeight / 2; // 初始化y坐标位置，用于填充表格内容
//                    for (int i = 0; i < 5; i++) { // 填充第一部分表格内容
//                        for (int j = 0; j < cols1; j++) {
//                            float colSpan = (j == 1) ? totalCols - 1 : 1; // 如果是第二列，跨度为总列数减一，否则为1
//                            float xPosition = margin + j * colWidth + cellMargin; // 当前单元格内容的x坐标位置
//                            cont.beginText(); // 开始文本模式
//                            cont.newLineAtOffset(xPosition, yPosition); // 设置文本的起始位置
//                            cont.showText("第" + (i + 1) + "行第" + (j + 1) + "列"); // 显示文本内容
//                            cont.endText(); // 结束文本模式
//                        }
//                        yPosition -= rowHeight; // 更新y坐标位置，移到下一行
//                    }

                    String [] BaseStr = { "付款银行","查询单位","业务种类","业务日期","付款合计" };
                    //记录下每个单元格的y轴位置,用于编入内容的时候录入
                    float[] OneContentPosition = new float[5];
                    // 填充表格内容
                    yPosition = yStart - cellMargin - rowHeight / 2; // 初始化y坐标位置，用于填充表格内容
                    for (int i = 0; i < 5; i++) { // 填充第一部分表格内容
                        float xPosition = margin + cellMargin;
                        //使用加粗字体来写字
                        drawBoldText(cont, BaseStr[i], xPosition, yPosition);
                        //记录下每个单元格的y轴位置,用于编入内容的时候录入
                        OneContentPosition[i] = yPosition;
                        yPosition -= rowHeight; // 更新y坐标位置，移到下一行
                    }

                    //这部分可以用来填充之后的数据
//                    //模拟插入第一部分第二列的数据
//                    String [] BaseStr1 = { "中国工商银行曲靖市分行","旧县-新站二","05-物资代扣","2024年03月19日","付款合计" };
//                    for (int i = 0; i < 5; i++) {
//                            float xPosition = margin + colWidth + cellMargin; // 当前单元格内容的x坐标位置
//                            cont.beginText(); // 开始文本模式
//                            cont.newLineAtOffset(xPosition, OneContentPosition[i]); // 设置文本的起始位置
//                            cont.showText(BaseStr1[i]); // 显示文本内容
//                            cont.endText(); // 结束文本模式
//                    }

                    String [] BaseStr2 = {"序号","交易日期","客户姓名","客户账号","付款金额","银行流水号"};
                    //填入第二部分固定内容
                    for (int j = 0; j < cols2; j++){
                        float xPosition = margin + j * colWidth + cellMargin; // 当前单元格内容的x坐标位置
                        drawBoldText(cont, BaseStr2[j], xPosition, yPosition);
                    }

                    yPosition -= rowHeight;

                    for (int i = 0; i < 2; i++) { // 填充第三部分表格内容
                        for (int j = 0; j < cols2; j++) {
                            float xPosition = margin + j * colWidth + cellMargin; // 当前单元格内容的x坐标位置
                            cont.beginText(); // 开始文本模式
                            cont.newLineAtOffset(xPosition, yPosition); // 设置文本的起始位置
                            cont.showText("第" + (i + 6) + "行第" + (j + 1) + "列"); // 显示文本内容
                            cont.endText(); // 结束文本模式
                        }
                        yPosition -= rowHeight; // 更新y坐标位置，移到下一行
                    }

                    PDImageXObject Image = PDImageXObject.createFromFile("src/main/resources/zhang.png",doc);
                    // 计算印章图片位置
                    float scale = 0.5f; // 图片缩放比例
                    float imageWidth = Image.getWidth() ;
                    float imageHeight = Image.getHeight();
                    float imagePositionY = PDRectangle.A4.getWidth() - 250; // 右下角，距离右边和底部一定距离
                    float imagePositionX = 480;

                    // 添加图片到页面
                    cont.drawImage(Image, imagePositionX, imagePositionY, imageWidth, imageHeight);

                    cont.close();
                }

                doc.save("src/main/resources/wwii.pdf");
            }
        }

    // 模拟加粗文字的方法
    private static void drawBoldText(PDPageContentStream contentStream, String text, float x, float y) throws IOException {
        final float boldness = 0.2f; // 通过调节这个值可以改变加粗程度

        // 在不同方向重复绘制文本，产生加粗效果
        for (float dx = -boldness; dx <= boldness; dx += boldness / 2) {
            for (float dy = -boldness; dy <= boldness; dy += boldness / 2) {
                contentStream.beginText();
                contentStream.newLineAtOffset(x + dx, y + dy);
                contentStream.showText(text);
                contentStream.endText();
            }
        }
    }
}
