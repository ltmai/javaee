package com.example.demo;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class InjectCollection {
   
   List<Integer> intList;
   Set<String>  stringSet;
   Map<Integer, String>  indexMap;
   Properties properties;

   public List<Integer> getIntList() {
      return intList;
   }

   public void setIntList(List<Integer> intList) {
      this.intList = intList;
   }

   public Set<String> getStringSet() {
      return stringSet;
   }

   public void setStringSet(Set<String> stringSet) {
      this.stringSet = stringSet;
   }

   public Map<Integer, String> getIndexMap() {
      return indexMap;
   }

   public void setIndexMap(Map<Integer, String> indexMap) {
      this.indexMap = indexMap;
   }

   public Properties getProperties() {
      return properties;
   }

   public void setProperties(Properties properties) {
      this.properties = properties;
   }

}