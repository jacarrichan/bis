package com.avicit.framework.web.support.image;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class ImageResponseWrapper extends HttpServletResponseWrapper {

    protected HttpServletResponse origResponse = null;
    protected ServletOutputStream stream = null;
    protected PrintWriter writer = null;
    protected String imageType = null;

    public ImageResponseWrapper(HttpServletResponse response, String imageType) {
        super(response);
        origResponse = response;
        this.imageType = imageType;
    }

    public ServletOutputStream createOutputStream() throws IOException {
        return (new ImageResponseStream(origResponse, imageType));
    }

    public void finishResponse() {
        try {
            if (writer != null) {
                writer.close();
            }
            else {
                if (stream != null) {
                    stream.close();
                }
            }
        }
        catch (IOException e) {
        }
    }

    @Override
    public void flushBuffer() throws IOException {
        stream.flush();
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        if (writer != null) {
            throw new IllegalStateException("getWriter() has already been called!");
        }

        if (stream == null) stream = createOutputStream();
        return (stream);
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        if (writer != null) {
            return (writer);
        }

        if (stream != null) {
            throw new IllegalStateException("getOutputStream() has already been called!");
        }

        stream = createOutputStream();
        writer = new PrintWriter(new OutputStreamWriter(stream, "UTF-8"));
        return (writer);
    }

    @Override
    public void setContentLength(int length) {
    }
}
