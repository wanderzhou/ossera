package training.c7;


/**
* training/c7/_SimpleTransferStub.java .
* 由IDL-to-Java 编译器 (可移植), 版本 "3.2"生成
* 从SimpleTransfer.idl
* 2014年12月16日 星期二 下午04时07分21秒 CST
*/

public class _SimpleTransferStub extends org.omg.CORBA.portable.ObjectImpl implements training.c7.SimpleTransfer
{

  public boolean putContent (String fileName, String content)
  {
            org.omg.CORBA.portable.InputStream $in = null;
            try {
                org.omg.CORBA.portable.OutputStream $out = _request ("putContent", true);
                $out.write_string (fileName);
                $out.write_string (content);
                $in = _invoke ($out);
                boolean $result = $in.read_boolean ();
                return $result;
            } catch (org.omg.CORBA.portable.ApplicationException $ex) {
                $in = $ex.getInputStream ();
                String _id = $ex.getId ();
                throw new org.omg.CORBA.MARSHAL (_id);
            } catch (org.omg.CORBA.portable.RemarshalException $rm) {
                return putContent (fileName, content        );
            } finally {
                _releaseReply ($in);
            }
  } // putContent

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:training/c7/SimpleTransfer:1.0"};

  public String[] _ids ()
  {
    return (String[])__ids.clone ();
  }

  private void readObject (java.io.ObjectInputStream s) throws java.io.IOException
  {
     String str = s.readUTF ();
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     org.omg.CORBA.Object obj = orb.string_to_object (str);
     org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl) obj)._get_delegate ();
     _set_delegate (delegate);
   } finally {
     orb.destroy() ;
   }
  }

  private void writeObject (java.io.ObjectOutputStream s) throws java.io.IOException
  {
     String[] args = null;
     java.util.Properties props = null;
     org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init (args, props);
   try {
     String str = orb.object_to_string (this);
     s.writeUTF (str);
   } finally {
     orb.destroy() ;
   }
  }
} // class _SimpleTransferStub
