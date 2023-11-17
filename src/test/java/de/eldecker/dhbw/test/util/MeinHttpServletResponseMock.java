package de.eldecker.dhbw.test.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collection;
import java.util.Locale;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Manuell erzeugte Mock-Klasse. Ein Objekt dieser Klasse wird als zweites
 * Argument für Aufrufe der Methode {@code doGet()} von Servlet-Klassen
 * benötigt.
 */
public class MeinHttpServletResponseMock implements HttpServletResponse {

    private String _contentType = "";
    
    /** HTTP Status Code */
    private int _status = -1;
    
    /** StringWriter, um Antwortzeilen, die von Servlet geschrieben werden, zu sammeln. */
    private StringWriter _stringWriter = new StringWriter();
    
    /** Für Ausgabe (Response) von Servlet. */ 
    private PrintWriter _printWriter = new PrintWriter(_stringWriter);
    

    /**
     * Getter für String, der auf von {@link #getWriter()} 
     * geschrieben wurde.
     * 
     * @return Payload string der response 
     */
    public String getStringWrittenToPrintWriter() {
        
        return _stringWriter.toString();
    }
    
    @Override
    public String getCharacterEncoding() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getContentType() {
        
        return _contentType;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        
        return _printWriter; 
    }

    @Override
    public void setCharacterEncoding(String charset) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setContentLength(int len) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setContentLengthLong(long len) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setContentType(String type) {

        _contentType = type;        
    }

    @Override
    public void setBufferSize(int size) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int getBufferSize() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void flushBuffer() throws IOException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void resetBuffer() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean isCommitted() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void reset() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setLocale(Locale loc) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Locale getLocale() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void addCookie(Cookie cookie) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean containsHeader(String name) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public String encodeURL(String url) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String encodeRedirectURL(String url) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String encodeUrl(String url) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String encodeRedirectUrl(String url) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void sendError(int sc, String msg) throws IOException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void sendError(int sc) throws IOException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void sendRedirect(String location) throws IOException {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setDateHeader(String name, long date) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void addDateHeader(String name, long date) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setHeader(String name, String value) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void addHeader(String name, String value) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setIntHeader(String name, int value) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void addIntHeader(String name, int value) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setStatus(int sc) {

        _status = sc;
    }

    @Override
    public void setStatus(int sc, String sm) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int getStatus() {
        
        return _status;
    }

    @Override
    public String getHeader(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<String> getHeaders(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Collection<String> getHeaderNames() {
        // TODO Auto-generated method stub
        return null;
    }

}
