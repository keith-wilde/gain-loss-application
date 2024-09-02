package com.kwilde.gainloss.service;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CSVFileServiceTest {

    /**
     * Use nested to be able to group tests by functionality. In this case by method
     */
    @Nested
    class ImportData {
        @Test
        void Should_Successfully_Import_Data(){
            assertTrue(true);
        }

        @Test
        void Should_Handle_Missing_File(){
            assertFalse(false);
        }
    }
}
