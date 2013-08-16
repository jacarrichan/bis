package com.avicit.framework.web.support.image;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.*;

public class ImageResponseStream extends ServletOutputStream {

    protected ByteArrayOutputStream baos = null;
    protected ByteArrayOutputStream imageBaos = null;
    protected OutputStream os = null;
    protected boolean closed = false;
    protected HttpServletResponse response = null;
    protected ServletOutputStream output = null;
    protected String imageType = null;

    public ImageResponseStream(HttpServletResponse response, String imageType) throws IOException {
        super();
        closed = false;
        this.response = response;
        this.output = response.getOutputStream();
        baos = new ByteArrayOutputStream();
        imageBaos = new ByteArrayOutputStream();
        os = new BufferedOutputStream(baos);
        this.imageType = imageType;
    }

    @Override
    public void close() throws IOException {
        if (closed) {
            throw new IOException("This output stream has already been closed");
        }
        os.flush();
        byte[] bytes = baos.toByteArray();
        BufferedImage srcImage = ImageIO.read(new ByteArrayInputStream(bytes));
        BufferedImage descImage = srcImage;
        //  ����ͼ��
        if (srcImage.getType() == BufferedImage.TYPE_BYTE_BINARY) {
            descImage = new BufferedImage(srcImage.getWidth(), srcImage.getHeight(), BufferedImage.TYPE_INT_RGB);
            descImage.createGraphics().drawImage(srcImage, null, 0, 0);
        }
        ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
        ColorConvertOp colorConvert = new ColorConvertOp(cs, cs, null);
        colorConvert.filter(descImage, descImage);
        ImageIO.write(descImage, this.imageType, imageBaos);
        bytes = imageBaos.toByteArray();
        response.setContentLength(bytes.length);
        output.write(bytes);
        output.flush();
        output.close();
        closed = true;

    }

    @Override
    public void flush() throws IOException {
        if (closed) {
            throw new IOException("Cannot flush a closed output stream");
        }
        os.flush();
    }

    @Override
    public void write(int b) throws IOException {
        if (closed) {
            throw new IOException("Cannot write to a closed output stream");
        }
        os.write((byte) b);
    }

    @Override
    public void write(byte b[]) throws IOException {
        write(b, 0, b.length);
    }

    @Override
    public void write(byte b[], int off, int len) throws IOException {
        if (closed) {
            throw new IOException("Cannot write to a closed output stream");
        }
        os.write(b, off, len);
    }

    public boolean closed() {
        return (this.closed);
    }

    public void reset() {
        //noop
    }
}
