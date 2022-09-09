// **********************************************************************
//
// Copyright (c) 2003-2006 ZeroC, Inc. All rights reserved.
//
// This copy of Ice is licensed to you under the terms described in the
// ICE_LICENSE file included in this distribution.
//
// **********************************************************************

// Ice version 3.1.1

package Demo;

public final class TimeOfDay implements java.lang.Cloneable
{
    public short hour;

    public short minute;

    public short second;

    public TimeOfDay()
    {
    }

    public TimeOfDay(short hour, short minute, short second)
    {
	this.hour = hour;
	this.minute = minute;
	this.second = second;
    }

    public boolean
    equals(java.lang.Object rhs)
    {
	if(this == rhs)
	{
	    return true;
	}
	TimeOfDay _r = null;
	try
	{
	    _r = (TimeOfDay)rhs;
	}
	catch(ClassCastException ex)
	{
	}

	if(_r != null)
	{
	    if(hour != _r.hour)
	    {
		return false;
	    }
	    if(minute != _r.minute)
	    {
		return false;
	    }
	    if(second != _r.second)
	    {
		return false;
	    }

	    return true;
	}

	return false;
    }

    public int
    hashCode()
    {
	int __h = 0;
	__h = 5 * __h + (int)hour;
	__h = 5 * __h + (int)minute;
	__h = 5 * __h + (int)second;
	return __h;
    }

    public java.lang.Object
    clone()
    {
	java.lang.Object o = null;
	try
	{
	    o = super.clone();
	}
	catch(CloneNotSupportedException ex)
	{
	    assert false; // impossible
	}
	return o;
    }

    public void
    __write(IceInternal.BasicStream __os)
    {
	__os.writeShort(hour);
	__os.writeShort(minute);
	__os.writeShort(second);
    }

    public void
    __read(IceInternal.BasicStream __is)
    {
	hour = __is.readShort();
	minute = __is.readShort();
	second = __is.readShort();
    }
}
