package training.c7;

/**
* training/c7/SimpleTransferHolder.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从SimpleTransfer.idl
* 2014年12月16日 星期二 下午04时07分21秒 CST
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
