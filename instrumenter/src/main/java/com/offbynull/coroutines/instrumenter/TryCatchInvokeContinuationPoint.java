/*
 * Copyright (c) 2016, Kasra Faghihi, All rights reserved.
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3.0 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library.
 */
package com.offbynull.coroutines.instrumenter;

import org.apache.commons.lang3.Validate;
import org.objectweb.asm.tree.LabelNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.TryCatchBlockNode;
import org.objectweb.asm.tree.analysis.BasicValue;
import org.objectweb.asm.tree.analysis.Frame;

class TryCatchInvokeContinuationPoint implements ContinuationPoint {

    private final Integer lineNumber;
    private final MethodInsnNode invokeInstruction;
    private final LabelNode continueExecutionLabel;
    private final LabelNode exceptionExecutionLabel;
    private final TryCatchBlockNode tryCatchBlock;
    private final Frame<BasicValue> frame;

    TryCatchInvokeContinuationPoint(
            Integer lineNumber,
            MethodInsnNode invokeInstruction,
            Frame<BasicValue> frame) {
        // lineNumber is null if it doesn't exist
        Validate.notNull(invokeInstruction);
        Validate.notNull(frame);

        this.lineNumber = lineNumber;
        this.invokeInstruction = invokeInstruction;
        this.continueExecutionLabel = new LabelNode();
        this.exceptionExecutionLabel = new LabelNode();
        this.tryCatchBlock = new TryCatchBlockNode(null, null, null, null);
        this.frame = frame;
    }

    @Override
    public Integer getLineNumber() {
        return lineNumber;
    }

    @Override
    public MethodInsnNode getInvokeInstruction() {
        return invokeInstruction;
    }

    @Override
    public LabelNode getContinueExecutionLabel() {
        return continueExecutionLabel;
    }

    public LabelNode getExceptionExecutionLabel() {
        return exceptionExecutionLabel;
    }

    public TryCatchBlockNode getTryCatchBlock() {
        return tryCatchBlock;
    }

    @Override
    public Frame<BasicValue> getFrame() {
        return frame;
    }
    
}
