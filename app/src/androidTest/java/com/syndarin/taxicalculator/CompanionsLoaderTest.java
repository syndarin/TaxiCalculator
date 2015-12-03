package com.syndarin.taxicalculator;

import android.content.Loader;
import android.test.LoaderTestCase;
import android.test.RenamingDelegatingContext;

import com.syndarin.taxicalculator.data.Companion;

import java.util.List;

/**
 * Created by syndarin on 8/31/15.
 */
public class CompanionsLoaderTest extends LoaderTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        RenamingDelegatingContext mockContext = new RenamingDelegatingContext(getContext(), "test_");
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
}
