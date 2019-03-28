/*
 * TagNotWritableException.java
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

package be.appfoundry.nfclibrary.exceptions;

/**
 * Exception created when a tag cannot be written to
 * @author Daneo Van Overloop
 * NfcLibrary
 * Created on 27/03/14.
 */
public class TagNotWritableException extends Exception {

    public TagNotWritableException(){}
    public TagNotWritableException(String message) {
        super();
    }
}
