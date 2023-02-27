package org.yologn.RhQuestEditor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class QuestIO {
    DataInputStream in = null;
    DataOutputStream out = null;
    public long bytesRead = 0;
    public QuestIO(DataInputStream in)
    {
        this.in = in;
    }

    public QuestIO(DataOutputStream out) {
        this.out = out;
    }


    public byte[] readBlock(int bytes) throws IOException {
        byte[] temp = new byte[bytes];
        bytesRead += in.read(temp,0,bytes);

        return temp;
    }
    public void writeBlock(byte[] arr) throws IOException {
        out.write(arr);
    }
    public  String getString(int cArraySize) throws IOException {
        String buffer = "";
        for (int j = 0; j < cArraySize; j++) {
            buffer += ((char) in.read());
        }
        bytesRead += cArraySize;
        return buffer;
    }
    public void skipBytes(int bytes) throws IOException {
        in.skipBytes(bytes);
        bytesRead += bytes;
    }
    public  String readLengthString() throws IOException {
        int len = readInt();
        String buffer = "";
        for (int j = 0; j < len; j++) {
            buffer += ((char) in.read());
        }
        bytesRead += len;
        return buffer;
    }
    public void writeLengthString(String s) throws IOException {
        int len = s.length();
        writeInt(len);
        out.write(Util.stringToBt(s,len));
    }
    public byte readByte() throws IOException {
        bytesRead ++;
        return in.readByte();
    }
    public void writeByte(byte b) throws IOException {
        out.writeByte(b);
    }
    public void flush() throws IOException {
        if(out != null)
            out.flush();
    }
    public long readInt64() throws IOException {
        bytesRead +=8;
        return Long.reverseBytes(in.readLong());
    }
    public void writeInt64(long val) throws IOException {
        out.writeLong(Long.reverseBytes(val));
    }
    public  int readInt() throws IOException {
        bytesRead += 4;
        return Integer.reverseBytes(in.readInt());
    }
    public void writeInt(int val) throws IOException {
        out.writeInt(Integer.reverseBytes(val));
    }
    public  short readShort() throws IOException {
        bytesRead += 2;
        return Short.reverseBytes(in.readShort());
    }
    public void writeShort(short val) throws IOException {
        out.writeShort(Short.reverseBytes(val));
    }



}
