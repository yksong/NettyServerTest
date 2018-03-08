package com.protocol;// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: DataFromServertoClient.proto

public final class DataFromServertoClient {
  private DataFromServertoClient() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
  }
  public interface UncompressDataOrBuilder
      extends com.google.protobuf.MessageOrBuilder {

    // required int32 lastframe = 1;
    /**
     * <code>required int32 lastframe = 1;</code>
     *
     * <pre>
     * ǰһ֡ͼ����������裩  
     * </pre>
     */
    boolean hasLastframe();
    /**
     * <code>required int32 lastframe = 1;</code>
     *
     * <pre>
     * ǰһ֡ͼ����������裩  
     * </pre>
     */
    int getLastframe();

    // required int32 currentframe = 2;
    /**
     * <code>required int32 currentframe = 2;</code>
     *
     * <pre>
     * ��ǰ֡ͼ����������裩  
     * </pre>
     */
    boolean hasCurrentframe();
    /**
     * <code>required int32 currentframe = 2;</code>
     *
     * <pre>
     * ��ǰ֡ͼ����������裩  
     * </pre>
     */
    int getCurrentframe();

    // repeated int32 uncompressDatas = 3;
    /**
     * <code>repeated int32 uncompressDatas = 3;</code>
     *
     * <pre>
     * ��ѹ������ĵ����ݣ����ϣ�  
     * </pre>
     */
    java.util.List<Integer> getUncompressDatasList();
    /**
     * <code>repeated int32 uncompressDatas = 3;</code>
     *
     * <pre>
     * ��ѹ������ĵ����ݣ����ϣ�  
     * </pre>
     */
    int getUncompressDatasCount();
    /**
     * <code>repeated int32 uncompressDatas = 3;</code>
     *
     * <pre>
     * ��ѹ������ĵ����ݣ����ϣ�  
     * </pre>
     */
    int getUncompressDatas(int index);
  }
  /**
   * Protobuf type {@code UncompressData}
   */
  public static final class UncompressData extends
      com.google.protobuf.GeneratedMessage
      implements UncompressDataOrBuilder {
    // Use UncompressData.newBuilder() to construct.
    private UncompressData(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
      this.unknownFields = builder.getUnknownFields();
    }
    private UncompressData(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

    private static final UncompressData defaultInstance;
    public static UncompressData getDefaultInstance() {
      return defaultInstance;
    }

    public UncompressData getDefaultInstanceForType() {
      return defaultInstance;
    }

    private final com.google.protobuf.UnknownFieldSet unknownFields;
    @Override
    public final com.google.protobuf.UnknownFieldSet
        getUnknownFields() {
      return this.unknownFields;
    }
    private UncompressData(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      initFields();
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            default: {
              if (!parseUnknownField(input, unknownFields,
                                     extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
            case 8: {
              bitField0_ |= 0x00000001;
              lastframe_ = input.readInt32();
              break;
            }
            case 16: {
              bitField0_ |= 0x00000002;
              currentframe_ = input.readInt32();
              break;
            }
            case 24: {
              if (!((mutable_bitField0_ & 0x00000004) == 0x00000004)) {
                uncompressDatas_ = new java.util.ArrayList<Integer>();
                mutable_bitField0_ |= 0x00000004;
              }
              uncompressDatas_.add(input.readInt32());
              break;
            }
            case 26: {
              int length = input.readRawVarint32();
              int limit = input.pushLimit(length);
              if (!((mutable_bitField0_ & 0x00000004) == 0x00000004) && input.getBytesUntilLimit() > 0) {
                uncompressDatas_ = new java.util.ArrayList<Integer>();
                mutable_bitField0_ |= 0x00000004;
              }
              while (input.getBytesUntilLimit() > 0) {
                uncompressDatas_.add(input.readInt32());
              }
              input.popLimit(limit);
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e.getMessage()).setUnfinishedMessage(this);
      } finally {
        if (((mutable_bitField0_ & 0x00000004) == 0x00000004)) {
          uncompressDatas_ = java.util.Collections.unmodifiableList(uncompressDatas_);
        }
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return DataFromServertoClient.internal_static_UncompressData_descriptor;
    }

    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return DataFromServertoClient.internal_static_UncompressData_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              UncompressData.class, Builder.class);
    }

    public static com.google.protobuf.Parser<UncompressData> PARSER =
        new com.google.protobuf.AbstractParser<UncompressData>() {
      public UncompressData parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new UncompressData(input, extensionRegistry);
      }
    };

    @Override
    public com.google.protobuf.Parser<UncompressData> getParserForType() {
      return PARSER;
    }

    private int bitField0_;
    // required int32 lastframe = 1;
    public static final int LASTFRAME_FIELD_NUMBER = 1;
    private int lastframe_;
    /**
     * <code>required int32 lastframe = 1;</code>
     *
     * <pre>
     * ǰһ֡ͼ����������裩  
     * </pre>
     */
    public boolean hasLastframe() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int32 lastframe = 1;</code>
     *
     * <pre>
     * ǰһ֡ͼ����������裩  
     * </pre>
     */
    public int getLastframe() {
      return lastframe_;
    }

    // required int32 currentframe = 2;
    public static final int CURRENTFRAME_FIELD_NUMBER = 2;
    private int currentframe_;
    /**
     * <code>required int32 currentframe = 2;</code>
     *
     * <pre>
     * ��ǰ֡ͼ����������裩  
     * </pre>
     */
    public boolean hasCurrentframe() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required int32 currentframe = 2;</code>
     *
     * <pre>
     * ��ǰ֡ͼ����������裩  
     * </pre>
     */
    public int getCurrentframe() {
      return currentframe_;
    }

    // repeated int32 uncompressDatas = 3;
    public static final int UNCOMPRESSDATAS_FIELD_NUMBER = 3;
    private java.util.List<Integer> uncompressDatas_;
    /**
     * <code>repeated int32 uncompressDatas = 3;</code>
     *
     * <pre>
     * ��ѹ������ĵ����ݣ����ϣ�  
     * </pre>
     */
    public java.util.List<Integer>
        getUncompressDatasList() {
      return uncompressDatas_;
    }
    /**
     * <code>repeated int32 uncompressDatas = 3;</code>
     *
     * <pre>
     * ��ѹ������ĵ����ݣ����ϣ�  
     * </pre>
     */
    public int getUncompressDatasCount() {
      return uncompressDatas_.size();
    }
    /**
     * <code>repeated int32 uncompressDatas = 3;</code>
     *
     * <pre>
     * ��ѹ������ĵ����ݣ����ϣ�  
     * </pre>
     */
    public int getUncompressDatas(int index) {
      return uncompressDatas_.get(index);
    }

    private void initFields() {
      lastframe_ = 0;
      currentframe_ = 0;
      uncompressDatas_ = java.util.Collections.emptyList();
    }
    private byte memoizedIsInitialized = -1;
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized != -1) return isInitialized == 1;

      if (!hasLastframe()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasCurrentframe()) {
        memoizedIsInitialized = 0;
        return false;
      }
      memoizedIsInitialized = 1;
      return true;
    }

    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      getSerializedSize();
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        output.writeInt32(1, lastframe_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        output.writeInt32(2, currentframe_);
      }
      for (int i = 0; i < uncompressDatas_.size(); i++) {
        output.writeInt32(3, uncompressDatas_.get(i));
      }
      getUnknownFields().writeTo(output);
    }

    private int memoizedSerializedSize = -1;
    public int getSerializedSize() {
      int size = memoizedSerializedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) == 0x00000001)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(1, lastframe_);
      }
      if (((bitField0_ & 0x00000002) == 0x00000002)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, currentframe_);
      }
      {
        int dataSize = 0;
        for (int i = 0; i < uncompressDatas_.size(); i++) {
          dataSize += com.google.protobuf.CodedOutputStream
            .computeInt32SizeNoTag(uncompressDatas_.get(i));
        }
        size += dataSize;
        size += 1 * getUncompressDatasList().size();
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSerializedSize = size;
      return size;
    }

    private static final long serialVersionUID = 0L;
    @Override
    protected Object writeReplace()
        throws java.io.ObjectStreamException {
      return super.writeReplace();
    }

    public static UncompressData parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static UncompressData parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static UncompressData parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static UncompressData parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static UncompressData parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static UncompressData parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }
    public static UncompressData parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input);
    }
    public static UncompressData parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseDelimitedFrom(input, extensionRegistry);
    }
    public static UncompressData parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return PARSER.parseFrom(input);
    }
    public static UncompressData parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return PARSER.parseFrom(input, extensionRegistry);
    }

    public static Builder newBuilder() { return Builder.create(); }
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder(UncompressData prototype) {
      return newBuilder().mergeFrom(prototype);
    }
    public Builder toBuilder() { return newBuilder(this); }

    @Override
    protected Builder newBuilderForType(
        BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code UncompressData}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder>
       implements UncompressDataOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return DataFromServertoClient.internal_static_UncompressData_descriptor;
      }

      protected FieldAccessorTable
          internalGetFieldAccessorTable() {
        return DataFromServertoClient.internal_static_UncompressData_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                UncompressData.class, Builder.class);
      }

      // Construct using DataFromServertoClient.UncompressData.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
        }
      }
      private static Builder create() {
        return new Builder();
      }

      public Builder clear() {
        super.clear();
        lastframe_ = 0;
        bitField0_ = (bitField0_ & ~0x00000001);
        currentframe_ = 0;
        bitField0_ = (bitField0_ & ~0x00000002);
        uncompressDatas_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000004);
        return this;
      }

      public Builder clone() {
        return create().mergeFrom(buildPartial());
      }

      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return DataFromServertoClient.internal_static_UncompressData_descriptor;
      }

      public UncompressData getDefaultInstanceForType() {
        return UncompressData.getDefaultInstance();
      }

      public UncompressData build() {
        UncompressData result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      public UncompressData buildPartial() {
        UncompressData result = new UncompressData(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
          to_bitField0_ |= 0x00000001;
        }
        result.lastframe_ = lastframe_;
        if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
          to_bitField0_ |= 0x00000002;
        }
        result.currentframe_ = currentframe_;
        if (((bitField0_ & 0x00000004) == 0x00000004)) {
          uncompressDatas_ = java.util.Collections.unmodifiableList(uncompressDatas_);
          bitField0_ = (bitField0_ & ~0x00000004);
        }
        result.uncompressDatas_ = uncompressDatas_;
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof UncompressData) {
          return mergeFrom((UncompressData)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(UncompressData other) {
        if (other == UncompressData.getDefaultInstance()) return this;
        if (other.hasLastframe()) {
          setLastframe(other.getLastframe());
        }
        if (other.hasCurrentframe()) {
          setCurrentframe(other.getCurrentframe());
        }
        if (!other.uncompressDatas_.isEmpty()) {
          if (uncompressDatas_.isEmpty()) {
            uncompressDatas_ = other.uncompressDatas_;
            bitField0_ = (bitField0_ & ~0x00000004);
          } else {
            ensureUncompressDatasIsMutable();
            uncompressDatas_.addAll(other.uncompressDatas_);
          }
          onChanged();
        }
        this.mergeUnknownFields(other.getUnknownFields());
        return this;
      }

      public final boolean isInitialized() {
        if (!hasLastframe()) {
          
          return false;
        }
        if (!hasCurrentframe()) {
          
          return false;
        }
        return true;
      }

      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        UncompressData parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (UncompressData) e.getUnfinishedMessage();
          throw e;
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      // required int32 lastframe = 1;
      private int lastframe_ ;
      /**
       * <code>required int32 lastframe = 1;</code>
       *
       * <pre>
       * ǰһ֡ͼ����������裩  
       * </pre>
       */
      public boolean hasLastframe() {
        return ((bitField0_ & 0x00000001) == 0x00000001);
      }
      /**
       * <code>required int32 lastframe = 1;</code>
       *
       * <pre>
       * ǰһ֡ͼ����������裩  
       * </pre>
       */
      public int getLastframe() {
        return lastframe_;
      }
      /**
       * <code>required int32 lastframe = 1;</code>
       *
       * <pre>
       * ǰһ֡ͼ����������裩  
       * </pre>
       */
      public Builder setLastframe(int value) {
        bitField0_ |= 0x00000001;
        lastframe_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 lastframe = 1;</code>
       *
       * <pre>
       * ǰһ֡ͼ����������裩  
       * </pre>
       */
      public Builder clearLastframe() {
        bitField0_ = (bitField0_ & ~0x00000001);
        lastframe_ = 0;
        onChanged();
        return this;
      }

      // required int32 currentframe = 2;
      private int currentframe_ ;
      /**
       * <code>required int32 currentframe = 2;</code>
       *
       * <pre>
       * ��ǰ֡ͼ����������裩  
       * </pre>
       */
      public boolean hasCurrentframe() {
        return ((bitField0_ & 0x00000002) == 0x00000002);
      }
      /**
       * <code>required int32 currentframe = 2;</code>
       *
       * <pre>
       * ��ǰ֡ͼ����������裩  
       * </pre>
       */
      public int getCurrentframe() {
        return currentframe_;
      }
      /**
       * <code>required int32 currentframe = 2;</code>
       *
       * <pre>
       * ��ǰ֡ͼ����������裩  
       * </pre>
       */
      public Builder setCurrentframe(int value) {
        bitField0_ |= 0x00000002;
        currentframe_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 currentframe = 2;</code>
       *
       * <pre>
       * ��ǰ֡ͼ����������裩  
       * </pre>
       */
      public Builder clearCurrentframe() {
        bitField0_ = (bitField0_ & ~0x00000002);
        currentframe_ = 0;
        onChanged();
        return this;
      }

      // repeated int32 uncompressDatas = 3;
      private java.util.List<Integer> uncompressDatas_ = java.util.Collections.emptyList();
      private void ensureUncompressDatasIsMutable() {
        if (!((bitField0_ & 0x00000004) == 0x00000004)) {
          uncompressDatas_ = new java.util.ArrayList<Integer>(uncompressDatas_);
          bitField0_ |= 0x00000004;
         }
      }
      /**
       * <code>repeated int32 uncompressDatas = 3;</code>
       *
       * <pre>
       * ��ѹ������ĵ����ݣ����ϣ�  
       * </pre>
       */
      public java.util.List<Integer>
          getUncompressDatasList() {
        return java.util.Collections.unmodifiableList(uncompressDatas_);
      }
      /**
       * <code>repeated int32 uncompressDatas = 3;</code>
       *
       * <pre>
       * ��ѹ������ĵ����ݣ����ϣ�  
       * </pre>
       */
      public int getUncompressDatasCount() {
        return uncompressDatas_.size();
      }
      /**
       * <code>repeated int32 uncompressDatas = 3;</code>
       *
       * <pre>
       * ��ѹ������ĵ����ݣ����ϣ�  
       * </pre>
       */
      public int getUncompressDatas(int index) {
        return uncompressDatas_.get(index);
      }
      /**
       * <code>repeated int32 uncompressDatas = 3;</code>
       *
       * <pre>
       * ��ѹ������ĵ����ݣ����ϣ�  
       * </pre>
       */
      public Builder setUncompressDatas(
          int index, int value) {
        ensureUncompressDatasIsMutable();
        uncompressDatas_.set(index, value);
        onChanged();
        return this;
      }
      /**
       * <code>repeated int32 uncompressDatas = 3;</code>
       *
       * <pre>
       * ��ѹ������ĵ����ݣ����ϣ�  
       * </pre>
       */
      public Builder addUncompressDatas(int value) {
        ensureUncompressDatasIsMutable();
        uncompressDatas_.add(value);
        onChanged();
        return this;
      }
      /**
       * <code>repeated int32 uncompressDatas = 3;</code>
       *
       * <pre>
       * ��ѹ������ĵ����ݣ����ϣ�  
       * </pre>
       */
      public Builder addAllUncompressDatas(
          Iterable<? extends Integer> values) {
        ensureUncompressDatasIsMutable();
        super.addAll(values, uncompressDatas_);
        onChanged();
        return this;
      }
      /**
       * <code>repeated int32 uncompressDatas = 3;</code>
       *
       * <pre>
       * ��ѹ������ĵ����ݣ����ϣ�  
       * </pre>
       */
      public Builder clearUncompressDatas() {
        uncompressDatas_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000004);
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:UncompressData)
    }

    static {
      defaultInstance = new UncompressData(true);
      defaultInstance.initFields();
    }

    // @@protoc_insertion_point(class_scope:UncompressData)
  }

  private static com.google.protobuf.Descriptors.Descriptor
    internal_static_UncompressData_descriptor;
  private static
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_UncompressData_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\034DataFromServertoClient.proto\"R\n\016Uncomp" +
      "ressData\022\021\n\tlastframe\030\001 \002(\005\022\024\n\014currentfr" +
      "ame\030\002 \002(\005\022\027\n\017uncompressDatas\030\003 \003(\005"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
      new com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner() {
        public com.google.protobuf.ExtensionRegistry assignDescriptors(
            com.google.protobuf.Descriptors.FileDescriptor root) {
          descriptor = root;
          internal_static_UncompressData_descriptor =
            getDescriptor().getMessageTypes().get(0);
          internal_static_UncompressData_fieldAccessorTable = new
            com.google.protobuf.GeneratedMessage.FieldAccessorTable(
              internal_static_UncompressData_descriptor,
              new String[] { "Lastframe", "Currentframe", "UncompressDatas", });
          return null;
        }
      };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        }, assigner);
  }

  // @@protoc_insertion_point(outer_class_scope)
}