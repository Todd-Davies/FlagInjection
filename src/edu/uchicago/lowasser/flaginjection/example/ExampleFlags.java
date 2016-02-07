package edu.uchicago.lowasser.flaginjection.example;

import javax.annotation.Nullable;

import com.google.inject.Inject;

import edu.uchicago.lowasser.flaginjection.Flag;
import edu.uchicago.lowasser.flaginjection.FlagsClass;

final class ExampleFlags extends FlagsClass {
  
  private static String stringWithDefault = "default";
  private static int flaggedInteger;
  
  @Inject
  private ExampleFlags(
      @Flag(name = "flaggedInteger", description = "Required integer-valued flag")
      int flaggedInteger,
      @Nullable
      @Flag(name = "stringWithDefault", optional = true, description = "If this is omitted " +
        "from the command line, it isn't injected and stays on the default value")
      String stringWithDefault) {
    ExampleFlags.flaggedInteger = flaggedInteger;
    if (stringWithDefault != null) {
      ExampleFlags.stringWithDefault = stringWithDefault;
    }
  }
  
  static String getStringWithDefault() {
    return stringWithDefault;
  }
  
  static int getFlaggedInteger() {
    return flaggedInteger;
  }

}
