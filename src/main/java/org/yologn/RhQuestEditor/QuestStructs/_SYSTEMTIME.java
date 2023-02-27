package org.yologn.RhQuestEditor.QuestStructs;

import org.yologn.RhQuestEditor.QuestIO;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class _SYSTEMTIME {
    public short wYear;
    public short wMonth;
    public short wDayOfWeek;
    public short wDay;
    public short wHour;
    public short wMinute;
    public short wSecond;
    public short wMilliseconds;

    public void Load(QuestIO reader) throws IOException {
        wYear = reader.readShort();
        wMonth = reader.readShort();
        wDayOfWeek = reader.readShort();
        wDay = reader.readShort();
        wHour = reader.readShort();
        wMinute = reader.readShort();
        wSecond = reader.readShort();
        wMilliseconds = reader.readShort();

    }
    public void setDate(LocalDate date)
    {
        wYear = (short)date.getYear();
        wMonth = (short)date.getMonthValue();
        wDayOfWeek = (short)date.getDayOfWeek().getValue();
        wDay = (short)date.getDayOfMonth();
    }
    public void setTime(LocalTime time)
    {
        wHour = (short)time.getHour();
        wMinute = (short)time.getMinute();
        wSecond = (short)time.getSecond();
    }
    public void write(QuestIO writer) throws IOException {
        writer.writeShort(wYear);
        writer.writeShort(wMonth);
        writer.writeShort(wDayOfWeek);
        writer.writeShort(wDay);
        writer.writeShort(wHour);
        writer.writeShort(wMinute);
        writer.writeShort(wSecond);
        writer.writeShort(wMilliseconds);
    }

    public _SYSTEMTIME(_SYSTEMTIME other) {
        this.wYear = other.wYear;
        this.wMonth = other.wMonth;
        this.wDayOfWeek = other.wDayOfWeek;
        this.wDay = other.wDay;
        this.wHour = other.wHour;
        this.wMinute = other.wMinute;
        this.wSecond = other.wSecond;
        this.wMilliseconds = other.wMilliseconds;
    }
    public _SYSTEMTIME()
    {

    }
}
