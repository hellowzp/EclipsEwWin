/***
 * Excerpted from "Modular Java",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/cwosg for more book information.
***/

package dwmj.domain;


import java.util.Set;

import org.compass.annotations.Index;
import org.compass.annotations.Searchable;
import org.compass.annotations.SearchableId;
import org.compass.annotations.SearchableProperty;
import org.compass.annotations.Store;

@Searchable(alias = "jar")


public class JarFile {
   
   @SearchableProperty(store = Store.YES, index = Index.UN_TOKENIZED)
   
   private String repository;
   
   @SearchableProperty(store = Store.YES, index = Index.TOKENIZED)
   
   private String groupId;
   
   @SearchableProperty(store = Store.YES, index = Index.TOKENIZED)
   
   private String artifactId;
   
   @SearchableProperty(store = Store.YES, index = Index.UN_TOKENIZED)
   
   private String version;
   
   @SearchableProperty(store = Store.YES, index = Index.UN_TOKENIZED)
   
   private boolean snapshot;
   
   @SearchableProperty(store = Store.YES, index = Index.UN_TOKENIZED)
   
   private String rawUrl;
   
   @SearchableProperty(store = Store.YES, index = Index.UN_TOKENIZED)
   
   private boolean hasSource;
   
   @SearchableProperty(store = Store.YES, index = Index.UN_TOKENIZED)
   
   private boolean hasJavadoc;
   
   @SearchableProperty(store = Store.YES, index = Index.UN_TOKENIZED)
   
   private String bundleSymbolicName;

   
   @SearchableProperty(store = Store.YES, index = Index.TOKENIZED)
   
   private Set<String> packages;
      
   
   @SearchableProperty(store = Store.YES, index = Index.TOKENIZED)
   
   private Set<String> fullClassNames;

   
   @SearchableProperty(store = Store.YES, index = Index.TOKENIZED)
   
   private Set<String> classNames;
   
   
   public String getRepository() {
      return repository;
   }

   public void setRepository(String repository) {
      this.repository = repository;
   }

   public String getGroupId() {
      return groupId;
   }

   public void setGroupId(String groupId) {
      this.groupId = groupId;
   }

   public String getArtifactId() {
      return artifactId;
   }

   public void setArtifactId(String artifactId) {
      this.artifactId = artifactId;
   }

   public String getVersion() {
      return version;
   }

   public void setVersion(String version) {
      this.version = version;
   }

   public boolean isSnapshot() {
      return snapshot;
   }

   public void setSnapshot(boolean snapshot) {
      this.snapshot = snapshot;
   }

   public boolean isBundle() {
      return bundleSymbolicName != null;
   }

   
   
   @SearchableId
   
   public String getRawUrl() {
      return rawUrl;
   }

   public void setRawUrl(String rawUrl) {
      this.rawUrl = rawUrl;
   }

   public boolean isHasSource() {
      return hasSource;
   }

   public void setHasSource(boolean hasSource) {
      this.hasSource = hasSource;
   }

   public boolean isHasJavadoc() {
      return hasJavadoc;
   }

   public void setHasJavadoc(boolean hasJavadoc) {
      this.hasJavadoc = hasJavadoc;
   }

   public String getBundleSymbolicName() {
      return bundleSymbolicName;
   }

   public void setBundleSymbolicName(String bundleSymbolicName) {
      this.bundleSymbolicName = bundleSymbolicName;
   }

   public Set<String> getPackages() {
      return packages;
   }

   public void setPackages(Set<String> packages) {
      this.packages = packages;
   }

   public Set<String> getFullClassNames() {
      return fullClassNames;
   }

   public void setFullClassNames(Set<String> fullClassNames) {
      this.fullClassNames = fullClassNames;
   }

   public boolean isIndexable() {
      return rawUrl != null;
   }

   public void setClassNames(Set<String> classNames) {
	  this.classNames = classNames;
   }

   public Set<String> getClassNames() {
	  return classNames;
   }
}


