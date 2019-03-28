/*
 * TestUtilities.java
 * NfcLibrary project.
 *
 * Created by : Daneo van Overloop - 17/6/2014.
 *
 * The MIT License (MIT)
 *
 * Copyright (c) 2014 AppFoundry. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 */

package be.appfoundry.nfclibrary.utilities;

import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.nfc.tech.TagTechnology;
import android.os.Bundle;
import android.os.Parcel;

import org.jetbrains.annotations.NotNull;

import be.appfoundry.nfclibrary.constants.NfcPayloadHeader;
import be.appfoundry.nfclibrary.exceptions.InsufficientCapacityException;
import be.appfoundry.nfclibrary.exceptions.ReadOnlyTagException;
import be.appfoundry.nfclibrary.utilities.interfaces.NdefWrite;
import be.appfoundry.nfclibrary.utilities.interfaces.NfcWriteUtility;
import be.appfoundry.nfclibrary.utilities.sync.NfcWriteUtilityImpl;
import be.appfoundry.nfclibrary.utilities.sync.WriteUtilityImpl;

/**
 * NfcLibrary by daneo
 * Created on 14/04/14.
 */
public class TestUtilities {

    public static final String NDEF_FORMATABLE = "NDEF_FORMATABLE";
    public static final String NDEF = "NDEF";
    public NdefMessage resultMessage;

    public TestUtilities() {
    }


    public boolean checkResult(@NotNull String expected) {
        if (resultMessage == null)
            return false;
        byte[] payload = resultMessage.getRecords()[0].getPayload();
        if (checkPayloadHeader(NfcPayloadHeader.CUSTOM_SCHEME)) {
            return new String(payload).contains(expected);
        } else {
            return new String(payload, 1, payload.length - 1).contains(expected);
        }
    }

    public NfcWriteUtility mockNdefWriteUtility() {
        final NdefWrite ndefWrite = new NdefWrite() {
            @Override
            public boolean writeToNdef(NdefMessage message, Ndef ndef) throws ReadOnlyTagException, InsufficientCapacityException, FormatException {
                resultMessage = message;
                return true;
            }

            @Override
            public boolean writeToNdefAndMakeReadonly(NdefMessage message, Ndef ndef) throws ReadOnlyTagException, InsufficientCapacityException, FormatException {
                resultMessage = message;
                return true;
            }

            @Override
            public boolean writeToNdefFormatableAndMakeReadonly(NdefMessage message, NdefFormatable ndefFormat) throws FormatException {
                return false;
            }

            @Override
            public boolean writeToNdefFormatable(NdefMessage message, NdefFormatable ndefFormatable) throws FormatException {
                return false;
            }
        };
        return getNfcWriteUtility(ndefWrite);
    }

    private NfcWriteUtilityImpl getNfcWriteUtility(NdefWrite ndefWrite) {
        return new NfcWriteUtilityImpl(new WriteUtilityImpl(ndefWrite));
    }

    public NfcWriteUtility mockNdefFormatableWrite() {
        final NdefWrite ndefWrite = new NdefWrite() {
            @Override
            public boolean writeToNdef(NdefMessage message, Ndef ndef) throws ReadOnlyTagException, InsufficientCapacityException, FormatException {
                return false;
            }

            @Override
            public boolean writeToNdefAndMakeReadonly(NdefMessage message, Ndef ndef) throws ReadOnlyTagException, InsufficientCapacityException, FormatException {
                return false;
            }

            @Override
            public boolean writeToNdefFormatableAndMakeReadonly(NdefMessage message, NdefFormatable ndefFormat) throws FormatException {
                resultMessage = message;
                return true;
            }

            @Override
            public boolean writeToNdefFormatable(NdefMessage message, NdefFormatable ndefFormatable) throws FormatException {
                resultMessage = message;
                return true;
            }
        };
        return new NfcWriteUtilityImpl(new WriteUtilityImpl(ndefWrite)) {
        };
    }

    public Tag mockTag(String technology) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        // For future reference

        // Parameters :
        byte[] b = {0x8};
        String ndefClass = "android.nfc.tech.Ndef";

        // FieldName which marks the capacity of the tag
        String extraNdefMaxlength = (String) Class.forName(ndefClass).getField("EXTRA_NDEF_MAXLENGTH").get(null);

        // FieldName which marks the tags writability
        String cardWritableStateField = (String) Class.forName(ndefClass).getField("EXTRA_NDEF_CARDSTATE").get(null);

        // Field to mark tag R/W
        int cardWritable = Class.forName(ndefClass).getField("NDEF_MODE_READ_WRITE").getInt(null);

        Bundle techExtras = new Bundle();

        techExtras.putInt(extraNdefMaxlength, 2048);
        techExtras.putInt(cardWritableStateField, cardWritable);
        Bundle[] extras = {techExtras};
        int[] technologies = {TagTechnology.class.getField(technology.toUpperCase()).getInt(null)}; //https://android.googlesource.com/platform/frameworks/base.git/+/android-4.3_r2/core/java/android/nfc/tech/TagTechnology.java


        // https://android.googlesource.com/platform/frameworks/base/+/master/core/java/android/nfc/Tag.java :376
        Parcel parcel = Parcel.obtain();

        parcel.writeByteArray(b); //Sets the ID
        parcel.writeIntArray(technologies); // Sets the technology to NDEF
        parcel.writeArray(extras); // Needed to set properties for NDEF tag
        parcel.writeInt(1); // Service handle
        parcel.writeInt(1); // Indicating a mock

        return Tag.CREATOR.createFromParcel(parcel);
    }

    @NotNull
    public NfcWriteUtility determineMockType(String technology) {

        return technology == null ? mockFailNdefWrite() : ( technology.equals(NDEF)) ? mockNdefWriteUtility() : mockNdefFormatableWrite();

    }

    public NfcWriteUtility mockFailNdefWrite() {
        return getNfcWriteUtility(new NdefWrite() {
            @Override
            public boolean writeToNdef(NdefMessage message, Ndef ndef) throws ReadOnlyTagException, InsufficientCapacityException, FormatException {
                return false;
            }

            @Override
            public boolean writeToNdefAndMakeReadonly(NdefMessage message, Ndef ndef) throws ReadOnlyTagException, InsufficientCapacityException, FormatException {
                return false;
            }

            @Override
            public boolean writeToNdefFormatable(NdefMessage message, NdefFormatable ndefFormatable) throws FormatException {
                return false;
            }

            @Override
            public boolean writeToNdefFormatableAndMakeReadonly(NdefMessage message, NdefFormatable ndefFormat) throws FormatException {
                return false;
            }
        });
    }

    public boolean checkPayloadHeader(byte expectedHeader) {
        return resultMessage!= null && resultMessage.getRecords()[0].getPayload()[0] == expectedHeader;
    }
}
