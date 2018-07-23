package com.training.weather.ingestor.infrastructure.util;

import io.lettuce.core.codec.RedisCodec;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

public class ObjectCodec<K, V> implements RedisCodec<K, V> {
  @Override
  public K decodeKey(ByteBuffer byteBuffer) {
    K key = null;

    ByteArrayInputStream byteArrayInputStream = null;
    ObjectInputStream objectInputStream = null;

    try {
      byteArrayInputStream = new ByteArrayInputStream(byteBuffertoByteArray(byteBuffer));
      objectInputStream = new ObjectInputStream(byteArrayInputStream);

      key = (K) objectInputStream.readObject();
    } catch (IOException | ClassNotFoundException exception) {
      exception.printStackTrace();
    } finally {
      try {
        if (byteArrayInputStream != null) {
          byteArrayInputStream.close();
        }
        if (objectInputStream != null) {
          objectInputStream.close();
        }
      } catch (IOException ioException) {
        ioException.printStackTrace();
      }
    }
    return key;
  }

  @Override
  public V decodeValue(ByteBuffer byteBuffer) {
    V value = null;

    ByteArrayInputStream byteArrayInputStream = null;
    ObjectInputStream objectInputStream = null;

    try {
      byteArrayInputStream = new ByteArrayInputStream(byteBuffertoByteArray(byteBuffer));
      objectInputStream = new ObjectInputStream(byteArrayInputStream);

      value = (V) objectInputStream.readObject();
    } catch (IOException | ClassNotFoundException exception) {
      exception.printStackTrace();
    } finally {
      try {
        if (byteArrayInputStream != null) {
          byteArrayInputStream.close();
        }
        if (objectInputStream != null) {
          objectInputStream.close();
        }
      } catch (IOException ioException) {
        ioException.printStackTrace();
      }
    }
    return value;
  }

  @Override
  public ByteBuffer encodeKey(K key) {
    byte[] bytes = null;

    ByteArrayOutputStream byteArrayOutputStream = null;
    ObjectOutputStream objectOutputStream = null;

    try {
      byteArrayOutputStream = new ByteArrayOutputStream();
      objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);

      objectOutputStream.writeObject(key);
      objectOutputStream.flush();

      bytes = byteArrayOutputStream.toByteArray();
    } catch (IOException ioException) {
      ioException.printStackTrace();
    } finally {
      try {
        if (byteArrayOutputStream != null) {
          byteArrayOutputStream.close();
        }
        if (objectOutputStream != null) {
          objectOutputStream.close();
        }
      } catch (IOException ioException) {
        ioException.printStackTrace();
      }
    }

    return ByteBuffer.wrap(bytes);
  }


  @Override
  public ByteBuffer encodeValue(V value) {
    byte[] bytes = null;

    ByteArrayOutputStream byteArrayOutputStream = null;
    ObjectOutputStream objectOutputStream = null;

    try {
      byteArrayOutputStream = new ByteArrayOutputStream();
      objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);

      objectOutputStream.writeObject(value);
      objectOutputStream.flush();

      bytes = byteArrayOutputStream.toByteArray();
    } catch (IOException ioException) {
      ioException.printStackTrace();
    } finally {
      try {
        if (byteArrayOutputStream != null) {
          byteArrayOutputStream.close();
        }
        if (objectOutputStream != null) {
          objectOutputStream.close();
        }
      } catch (IOException ioException) {
        ioException.printStackTrace();
      }
    }

    return ByteBuffer.wrap(bytes);
  }

  private byte[] byteBuffertoByteArray(ByteBuffer byteBuffer) {
    byte[] bytes = new byte[byteBuffer.remaining()];

    byteBuffer.get(bytes);

    return bytes;
  }
}