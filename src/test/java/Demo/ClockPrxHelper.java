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

public final class ClockPrxHelper extends Ice.ObjectPrxHelperBase implements ClockPrx
{
    public TimeOfDay
    getTime()
    {
	return getTime(__defaultContext());
    }

    public TimeOfDay
    getTime(java.util.Map __ctx)
    {
	int __cnt = 0;
	while(true)
	{
	    try
	    {
		__checkTwowayOnly("getTime");
		Ice._ObjectDel __delBase = __getDelegate();
		_ClockDel __del = (_ClockDel)__delBase;
		return __del.getTime(__ctx);
	    }
	    catch(IceInternal.LocalExceptionWrapper __ex)
	    {
		__handleExceptionWrapper(__ex);
	    }
	    catch(Ice.LocalException __ex)
	    {
		__cnt = __handleException(__ex, __cnt);
	    }
	}
    }

    public void
    setTime(TimeOfDay time)
    {
	setTime(time, __defaultContext());
    }

    public void
    setTime(TimeOfDay time, java.util.Map __ctx)
    {
	int __cnt = 0;
	while(true)
	{
	    try
	    {
		Ice._ObjectDel __delBase = __getDelegate();
		_ClockDel __del = (_ClockDel)__delBase;
		__del.setTime(time, __ctx);
		return;
	    }
	    catch(IceInternal.LocalExceptionWrapper __ex)
	    {
		__handleExceptionWrapper(__ex);
	    }
	    catch(Ice.LocalException __ex)
	    {
		__cnt = __handleException(__ex, __cnt);
	    }
	}
    }

    public static ClockPrx
    checkedCast(Ice.ObjectPrx __obj)
    {
	ClockPrx __d = null;
	if(__obj != null)
	{
	    try
	    {
		__d = (ClockPrx)__obj;
	    }
	    catch(ClassCastException ex)
	    {
		if(__obj.ice_isA("::Demo::Clock"))
		{
		    ClockPrxHelper __h = new ClockPrxHelper();
		    __h.__copyFrom(__obj);
		    __d = __h;
		}
	    }
	}
	return __d;
    }

    public static ClockPrx
    checkedCast(Ice.ObjectPrx __obj, java.util.Map __ctx)
    {
	ClockPrx __d = null;
	if(__obj != null)
	{
	    try
	    {
		__d = (ClockPrx)__obj;
	    }
	    catch(ClassCastException ex)
	    {
		if(__obj.ice_isA("::Demo::Clock", __ctx))
		{
		    ClockPrxHelper __h = new ClockPrxHelper();
		    __h.__copyFrom(__obj);
		    __d = __h;
		}
	    }
	}
	return __d;
    }

    public static ClockPrx
    checkedCast(Ice.ObjectPrx __obj, String __facet)
    {
	ClockPrx __d = null;
	if(__obj != null)
	{
	    Ice.ObjectPrx __bb = __obj.ice_facet(__facet);
	    try
	    {
		if(__bb.ice_isA("::Demo::Clock"))
		{
		    ClockPrxHelper __h = new ClockPrxHelper();
		    __h.__copyFrom(__bb);
		    __d = __h;
		}
	    }
	    catch(Ice.FacetNotExistException ex)
	    {
	    }
	}
	return __d;
    }

    public static ClockPrx
    checkedCast(Ice.ObjectPrx __obj, String __facet, java.util.Map __ctx)
    {
	ClockPrx __d = null;
	if(__obj != null)
	{
	    Ice.ObjectPrx __bb = __obj.ice_facet(__facet);
	    try
	    {
		if(__bb.ice_isA("::Demo::Clock", __ctx))
		{
		    ClockPrxHelper __h = new ClockPrxHelper();
		    __h.__copyFrom(__bb);
		    __d = __h;
		}
	    }
	    catch(Ice.FacetNotExistException ex)
	    {
	    }
	}
	return __d;
    }

    public static ClockPrx
    uncheckedCast(Ice.ObjectPrx __obj)
    {
	ClockPrx __d = null;
	if(__obj != null)
	{
	    ClockPrxHelper __h = new ClockPrxHelper();
	    __h.__copyFrom(__obj);
	    __d = __h;
	}
	return __d;
    }

    public static ClockPrx
    uncheckedCast(Ice.ObjectPrx __obj, String __facet)
    {
	ClockPrx __d = null;
	if(__obj != null)
	{
	    Ice.ObjectPrx __bb = __obj.ice_facet(__facet);
	    ClockPrxHelper __h = new ClockPrxHelper();
	    __h.__copyFrom(__bb);
	    __d = __h;
	}
	return __d;
    }

    protected Ice._ObjectDelM
    __createDelegateM()
    {
	return new _ClockDelM();
    }

    protected Ice._ObjectDelD
    __createDelegateD()
    {
	return new _ClockDelD();
    }

    public static void
    __write(IceInternal.BasicStream __os, ClockPrx v)
    {
	__os.writeProxy(v);
    }

    public static ClockPrx
    __read(IceInternal.BasicStream __is)
    {
	Ice.ObjectPrx proxy = __is.readProxy();
	if(proxy != null)
	{
	    ClockPrxHelper result = new ClockPrxHelper();
	    result.__copyFrom(proxy);
	    return result;
	}
	return null;
    }
}
