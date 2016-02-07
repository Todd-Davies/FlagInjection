/**
 * Copyright 2012 Louis Wasserman
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package edu.uchicago.lowasser.flaginjection.example;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import com.google.inject.Injector;
import edu.uchicago.lowasser.flaginjection.Flags;

/**
 * An example of flag injection.
 * 
 * @author Louis Wasserman
 */
public class ExampleFlaggedClass {
  private final int flaggedInteger;
  private final String stringWithDefault;

  ExampleFlaggedClass() {
    this.flaggedInteger = ExampleFlags.getFlaggedInteger();
    this.stringWithDefault = ExampleFlags.getStringWithDefault();
  }

  @Override
  public String toString() {
    return Objects
        .toStringHelper(this)
        .add("flaggedInteger", flaggedInteger)
        .add("stringWithDefault", stringWithDefault)
        .toString();
  }

  public static void main(String[] args) {
    Injector injector = Flags.bootstrapFlagInjector(
        args,
        ExampleFlaggedClass.class.getName(),
        ImmutableList.<String>of("edu.uchicago.lowasser.flaginjection.example"),
        new ExampleFlagsModule());
    ExampleFlaggedClass example = injector.getInstance(ExampleFlaggedClass.class);
    System.out.println(example);
  }
}
