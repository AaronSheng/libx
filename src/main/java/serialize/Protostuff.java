package serialize;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Protostuff {
    private static Map<Class<?>, Schema<?>> cachedSchema = new ConcurrentHashMap<Class<?>, Schema<?>>();

    public static <T> byte[] serialize(T obj) {
        try {
            Class<T> cls = (Class<T>) obj.getClass();
            Schema<T> schema = getSchema(cls);
            LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
            return ProtostuffIOUtil.toByteArray(obj, schema, buffer);
        } catch (Exception e) {
            throw new RuntimeException("serialize exception", e);
        }
    }

    public static <T> T deserialize(byte[] bytes, Class<T> cls) {
        try {
            T obj = cls.newInstance();
            Schema<T> schema = getSchema(cls);
            ProtostuffIOUtil.mergeFrom(bytes, obj, schema);
            return obj;
        } catch (Exception e) {
           throw new RuntimeException("deserialize exception", e);
        }
    }

    private static <T> Schema<T> getSchema(Class<T> cls) {
        Schema<T> schema = (Schema<T>) cachedSchema.get(cls);
        if (schema == null) {
            schema = RuntimeSchema.createFrom(cls);
            cachedSchema.put(cls, schema);
        }
        return schema;
    }
}
