/* Copyright 2010 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package codecleaver;

import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.MethodVisitor;

import java.util.HashSet;

public class SymbolReader implements IdClassVisitor {

  private final HashSet<Id> result = new HashSet<Id>();

  public SymbolReader() {
  }

  public HashSet<Id> getResult() {
    return result;
  }

  private void add(Id id) {
    result.add(id);
  }

  @Override public void visitFile(String fileName) {}

  @Override public void visit(TypeId id,
      int version,
      int access,
      String name,
      String signature,
      String superName,
      String[] interfaces) {
    add(id);
    // add containing package
    add(id.getParent());
  }

  @Override public FieldVisitor visitField(FieldId id,
      int access,
      String name,
      String desc,
      String signature,
      Object value) {
    add(id);
    return null;
  }


  @Override public MethodVisitor visitMethod(MethodId id,
      int access,
      String name,
      String desc,
      String signature,
      String[] exceptions) {
    add(id);
    return null;
  }

  @Override public void visitEnd() {}

  @Override public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
    return null;
  }

  @Override public void visitInnerClass(
      String name, String outerName, String innerName, int access) {
  }

  @Override public void visitOuterClass(String owner, String name, String desc) {
  }
}
