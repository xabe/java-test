package com.xabe.orika;

import java.util.List;

public interface Mapper {

    <T> List<T> mapList(List<?> source, Class<T> destinationClass);

    <T> T map(Object sourceObject, Class<T> destinationClass);

    <T> void map(Object sourceObject, T destinationObject);
}
