module Demo
{
    interface Printer
    {
        string printString(string s);
    };

    struct TimeOfDay
    {
        short hour;
        short minute;
        short second;
    };

    interface Clock
    {
        TimeOfDay getTime();
        void setTime(TimeOfDay time);
        nonmutating Printer* getPrinter();
    };
};