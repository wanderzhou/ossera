package training.c7;

/**
* training/c7/SimpleTransferHolder.java .
* ��IDL-to-Java ������ (����ֲ), �汾 "3.2"����
* ��SimpleTransfer.idl
* 2014��12��16�� ���ڶ� ����04ʱ07��21�� CST
*/

public final class SimpleTransferHolder implements org.omg.CORBA.portable.Streamable
{
  public training.c7.SimpleTransfer value = null;

  public SimpleTransferHolder ()
  {
  }

  public SimpleTransferHolder (training.c7.SimpleTransfer initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = training.c7.SimpleTransferHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    training.c7.SimpleTransferHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return training.c7.SimpleTransferHelper.type ();
  }

}
