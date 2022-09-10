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

public abstract class _ClockDisp extends Ice.ObjectImpl implements Clock
{
    protected void
    ice_copyStateFrom(Ice.Object __obj)
	throws java.lang.CloneNotSupportedException
    {
	throw new java.lang.CloneNotSupportedException();
    }

    public static final String[] __ids =
    {
	"::Demo::Clock",
	"::Ice::Object"
    };

    public boolean
    ice_isA(String s)
    {
	return java.util.Arrays.binarySearch(__ids, s) >= 0;
    }

    public boolean
    ice_isA(String s, Ice.Current __current)
    {
	return java.util.Arrays.binarySearch(__ids, s) >= 0;
    }

    public String[]
    ice_ids()
    {
	return __ids;
    }

    public String[]
    ice_ids(Ice.Current __current)
    {
	return __ids;
    }

    public String
    ice_id()
    {
	return __ids[0];
    }

    public String
    ice_id(Ice.Current __current)
    {
	return __ids[0];
    }

    public static String
    ice_staticId()
    {
	return __ids[0];
    }

    public final PrinterPrx
    getPrinter()
    {
	return getPrinter(null);
    }

    public final TimeOfDay
    getTime()
    {
	return getTime(null);
    }

    public final void
    setTime(TimeOfDay time)
    {
	setTime(time, null);
    }

    public static IceInternal.DispatchStatus
    ___getTime(Clock __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
	__checkMode(Ice.OperationMode.Normal, __current.mode);
	IceInternal.BasicStream __os = __inS.os();
	TimeOfDay __ret = __obj.getTime(__current);
	__ret.__write(__os);
	return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___setTime(Clock __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
	__checkMode(Ice.OperationMode.Normal, __current.mode);
	IceInternal.BasicStream __is = __inS.is();
	TimeOfDay time;
	time = new TimeOfDay();
	time.__read(__is);
	__obj.setTime(time, __current);
	return IceInternal.DispatchStatus.DispatchOK;
    }

    public static IceInternal.DispatchStatus
    ___getPrinter(Clock __obj, IceInternal.Incoming __inS, Ice.Current __current)
    {
	__checkMode(Ice.OperationMode.Nonmutating, __current.mode);
	IceInternal.BasicStream __os = __inS.os();
	PrinterPrx __ret = __obj.getPrinter(__current);
	PrinterPrxHelper.__write(__os, __ret);
	return IceInternal.DispatchStatus.DispatchOK;
    }

    private final static String[] __all =
    {
	"getPrinter",
	"getTime",
	"ice_id",
	"ice_ids",
	"ice_isA",
	"ice_ping",
	"setTime"
    };

    public IceInternal.DispatchStatus
    __dispatch(IceInternal.Incoming in, Ice.Current __current)
    {
	int pos = java.util.Arrays.binarySearch(__all, __current.operation);
	if(pos < 0)
	{
	    return IceInternal.DispatchStatus.DispatchOperationNotExist;
	}

	switch(pos)
	{
	    case 0:
	    {
		return ___getPrinter(this, in, __current);
	    }
	    case 1:
	    {
		return ___getTime(this, in, __current);
	    }
	    case 2:
	    {
		return ___ice_id(this, in, __current);
	    }
	    case 3:
	    {
		return ___ice_ids(this, in, __current);
	    }
	    case 4:
	    {
		return ___ice_isA(this, in, __current);
	    }
	    case 5:
	    {
		return ___ice_ping(this, in, __current);
	    }
	    case 6:
	    {
		return ___setTime(this, in, __current);
	    }
	}

	assert(false);
	return IceInternal.DispatchStatus.DispatchOperationNotExist;
    }
}
