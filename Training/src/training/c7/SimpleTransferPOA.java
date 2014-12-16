package training.c7;


/**
* training/c7/SimpleTransferPOA.java .
* ��IDL-to-Java ������ (����ֲ), �汾 "3.2"����
* ��SimpleTransfer.idl
* 2014��12��16�� ���ڶ� ����04ʱ07��21�� CST
*/

public abstract class SimpleTransferPOA extends org.omg.PortableServer.Servant
 implements training.c7.SimpleTransferOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("putContent", new java.lang.Integer (0));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // training/c7/SimpleTransfer/putContent
       {
         String fileName = in.read_string ();
         String content = in.read_string ();
         boolean $result = false;
         $result = this.putContent (fileName, content);
         out = $rh.createReply();
         out.write_boolean ($result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:training/c7/SimpleTransfer:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public SimpleTransfer _this() 
  {
    return SimpleTransferHelper.narrow(
    super._this_object());
  }

  public SimpleTransfer _this(org.omg.CORBA.ORB orb) 
  {
    return SimpleTransferHelper.narrow(
    super._this_object(orb));
  }


} // class SimpleTransferPOA
