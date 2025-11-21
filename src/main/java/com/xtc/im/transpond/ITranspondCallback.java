package com.xtc.im.transpond;

import java.io.*;

// DeepSeek写的（虽然好像没用到）

public interface ITranspondCallback {
    void onError(String str) throws RemoteException;
    void onSuccess(byte[] bArr, int i) throws RemoteException;

    public static abstract class a implements ITranspondCallback {
        private static final String f31426a = "com.xtc.im.transpond.ITranspondCallback";
        static final int f31427b = 1;
        static final int c = 2;

        public a() {
        }

        public static ITranspondCallback a(Object binder) {
            if (binder == null) {
                return null;
            }
            
            if (binder instanceof ITranspondCallback) {
                return (ITranspondCallback) binder;
            }
            return new C0158a(binder);
        }

        public boolean onTransact(int code, DataInputStream in, DataOutputStream out) throws RemoteException, IOException {
            switch (code) {
                case 1:
                    in.readUTF();
                    byte[] data = readByteArray(in);
                    int value = in.readInt();
                    onSuccess(data, value);
                    out.writeInt(0); // 模拟 writeNoException
                    writeByteArray(out, data);
                    return true;
                    
                case 2:
                    in.readUTF();
                    String error = in.readUTF();
                    onError(error);
                    out.writeInt(0);
                    return true;
                    
                default:
                    if (code == 1598968902) {
                        out.writeUTF(f31426a);
                        return true;
                    }
                    return false;
            }
        }
        
        private byte[] readByteArray(DataInputStream in) throws IOException {
            int length = in.readInt();
            if (length < 0) return null;
            byte[] bytes = new byte[length];
            in.readFully(bytes);
            return bytes;
        }
        
        private void writeByteArray(DataOutputStream out, byte[] data) throws IOException {
            if (data == null) {
                out.writeInt(-1);
            } else {
                out.writeInt(data.length);
                out.write(data);
            }
        }

        static class C0158a implements ITranspondCallback {
            private Object f31428a;

            C0158a(Object binder) {
                this.f31428a = binder;
            }

            public String a() {
                return a.f31426a;
            }

            public Object asBinder() {
                return f31428a;
            }

            @Override
            public void onSuccess(byte[] bArr, int i) throws RemoteException {
                try {
                    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
                    DataOutputStream out = new DataOutputStream(byteOut);
                    
                    out.writeUTF(a.f31426a);
                    writeByteArray(out, bArr);
                    out.writeInt(i);
                    
                    ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
                    DataInputStream in = new DataInputStream(byteIn);
                    
                    if (f31428a instanceof a) {
                        ((a) f31428a).onTransact(1, in, out);
                    }
                    
                    int exceptionCode = out.size() > 0 ? new DataInputStream(
                        new ByteArrayInputStream(byteOut.toByteArray())).readInt() : 0;
                    if (exceptionCode != 0) {
                        throw new RemoteException("Remote exception occurred");
                    }
                    
                } catch (IOException e) {
                    throw new RemoteException("Communication error", e);
                }
            }

            @Override
            public void onError(String str) throws RemoteException {
                try {
                    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
                    DataOutputStream out = new DataOutputStream(byteOut);
                    
                    out.writeUTF(a.f31426a);
                    out.writeUTF(str);
                    
                    if (f31428a instanceof a) {
                        ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
                        DataInputStream in = new DataInputStream(byteIn);
                        ((a) f31428a).onTransact(2, in, out);
                    }
                    
                    int exceptionCode = out.size() > 0 ? new DataInputStream(
                        new ByteArrayInputStream(byteOut.toByteArray())).readInt() : 0;
                    if (exceptionCode != 0) {
                        throw new RemoteException("Remote exception occurred");
                    }
                    
                } catch (IOException e) {
                    throw new RemoteException("Communication error", e);
                }
            }
            
            private void writeByteArray(DataOutputStream out, byte[] data) throws IOException {
                if (data == null) {
                    out.writeInt(-1);
                } else {
                    out.writeInt(data.length);
                    out.write(data);
                }
            }
        }
    }
}

class RemoteException extends Exception {
    public RemoteException() {
        super();
    }
    
    public RemoteException(String message) {
        super(message);
    }
    
    public RemoteException(String message, Throwable cause) {
        super(message, cause);
    }
}