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

public interface ClockPrx extends Ice.ObjectPrx
{
    public TimeOfDay getTime();
    public TimeOfDay getTime(java.util.Map __ctx);

    public void setTime(TimeOfDay time);
    public void setTime(TimeOfDay time, java.util.Map __ctx);
}
