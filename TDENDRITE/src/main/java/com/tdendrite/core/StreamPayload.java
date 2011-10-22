package com.tdendrite.core;

import java.io.*;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class StreamPayload<T> implements Serializable {

    public static final int ZIP_BUFFER_SIZE = 16384;
    private static final int STREAM_BUFFER_SIZE = 16384;

    private String id;
    private String className;
    private Long time;
    private String parameters;
    private byte[] payload;
    private boolean zipped = true;

    public boolean isZipped() {
        return zipped;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public StreamPayload(){
            this.zipped = true;
    }


    public StreamPayload(boolean zipped) {
        this.zipped = zipped;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public byte[] getPayload() {
        return payload;
    }

    public void setPayload(byte[] payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "id:" + this.id + "  className:" + this.className +" time:" + this.time + " parameters:" + this.parameters + " payload:" + new String(this.payload);
    }

    public boolean setTypedPayload(T object){
        boolean result = false;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutput out = new ObjectOutputStream(bos);
            out.writeObject( object );
            if ( this.zipped ){
                this.payload = this.zipByteArray( bos.toByteArray() );
            }else{
              this.payload = bos.toByteArray();
            }
            out.close();
            bos.close();
            result = true;
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            return result;
        }
    }

    public T getTypedPayLoad(){

        T variable = null;

       try {
           ByteArrayInputStream bos;
            if ( this.zipped ){
                bos = new ByteArrayInputStream( this.unzipByteArray(this.payload) );
            }else{
                bos = new ByteArrayInputStream( this.payload );
            }
            ObjectInput in = new ObjectInputStream(bos);
            variable = (T)in.readObject();
            in.close();
            bos.close();
            //System.out.println( variable.getClass().getName() + "|" + variable.getClass().getCanonicalName() );
        } catch (IOException e) {
            throw new IOException("Something wrong ",e);  //To change body of catch statement use File | Settings | File Templates.
        } finally {
            return variable;
        }
    }

    private byte[] zipByteArray(byte[] file)throws IOException{
        byte[] byReturn = null;
        Deflater oDeflate = new Deflater(Deflater.DEFLATED, false);
        oDeflate.setInput(file);
        oDeflate.finish();
        ByteArrayOutputStream oZipStream = new ByteArrayOutputStream();
        try {
          while (! oDeflate.finished() ){
            byte[] byRead = new byte[ZIP_BUFFER_SIZE];
            int iBytesRead = oDeflate.deflate(byRead);
            if (iBytesRead == byRead.length){
              oZipStream.write(byRead);
            }
            else {
              oZipStream.write(byRead, 0, iBytesRead);
            }
          }
          oDeflate.end();
          byReturn = oZipStream.toByteArray();
        }
        finally {
          oZipStream.close();
        }
        return byReturn;
    }


    private byte[] unzipByteArray(byte[] file)throws IOException, DataFormatException {
        byte[] byReturn = null;

        Inflater oInflate = new Inflater(false);
        oInflate.setInput(file);

        ByteArrayOutputStream oZipStream = new ByteArrayOutputStream();
        try {
          while (! oInflate.finished() ){
            byte[] byRead = new byte[ZIP_BUFFER_SIZE];
            int iBytesRead = oInflate.inflate(byRead);
            if (iBytesRead == byRead.length){
              oZipStream.write(byRead);
            }
            else {
              oZipStream.write(byRead, 0, iBytesRead);
            }
          }
          byReturn = oZipStream.toByteArray();
        }
        catch (DataFormatException ex){
          throw new IOException("Attempting to unzip file that is not zipped.");
        }
        finally {
          oZipStream.close();
        }
        return byReturn;
    }


}