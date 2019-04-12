package com.orientechnologies.orient.distributed.impl.structural;

import com.orientechnologies.orient.core.db.config.ONodeIdentity;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class OStructuralSharedConfiguration {

  private Map<ONodeIdentity, OStructuralNodeConfiguration> knownNodes;

  public void deserialize(DataInput input) throws IOException {
    int nKnowNodes = input.readInt();
    knownNodes = new HashMap<>();
    while (nKnowNodes-- > 0) {
      OStructuralNodeConfiguration node = new OStructuralNodeConfiguration();
      node.deserialize(input);
      knownNodes.put(node.getIdentity(), node);
    }
  }

  public void init() {
    knownNodes = new HashMap<>();
  }

  public void serialize(DataOutput output) throws IOException {
    output.writeInt(knownNodes.size());
    for (OStructuralNodeConfiguration node : knownNodes.values()) {
      node.serialize(output);
    }
  }

  public void addNode(OStructuralNodeConfiguration node) {
    knownNodes.put(node.getIdentity(), node);
  }

  public Collection<OStructuralNodeConfiguration> listDatabases() {
    return knownNodes.values();
  }
}
