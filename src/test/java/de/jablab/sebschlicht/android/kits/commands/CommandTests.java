package de.jablab.sebschlicht.android.kits.commands;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
    CommandTest.class, PlayCommandTest.class, RegisterCommandTest.class,
    SetVolumeCommandTest.class, StopCommandTest.class
})
public class CommandTests {
}
