package com.xabe.visitor;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

public class VisitorTest {

  @Test
  public void shouldCreateVisitor() throws Exception {
    //Given
    final Legacy legacy = mock(Legacy.class);
    final List<Visitable> visitables = List.of(new LocationRequestExecutor(legacy), new RouteRequestExecutor(legacy));
    final Visitor visitor = new RequestVisitor();

    //When
    visitables.forEach(visitable -> visitable.accept(visitor));

    //Then
    final InOrder inOrder = inOrder(legacy);
    inOrder.verify(legacy).location();
    inOrder.verify(legacy).route();
  }
}
