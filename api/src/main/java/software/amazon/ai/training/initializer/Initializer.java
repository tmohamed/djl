/*
 * Copyright 2019 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file except in compliance
 * with the License. A copy of the License is located at
 *
 * http://aws.amazon.com/apache2.0/
 *
 * or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES
 * OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package software.amazon.ai.training.initializer;

import software.amazon.ai.ndarray.NDArray;
import software.amazon.ai.ndarray.NDManager;
import software.amazon.ai.ndarray.types.DataType;
import software.amazon.ai.ndarray.types.Shape;
import software.amazon.ai.nn.Block;

/**
 * An interface representing an initialization method.
 *
 * <p>Used to initialize the {@link NDArray} parameters stored within a {@link Block}.
 */
public interface Initializer {

    Initializer ZEROS = (m, s, t) -> m.zeros(s, t, m.getContext());
    Initializer ONES = (m, s, t) -> m.ones(s, t, m.getContext());

    /**
     * Initializes a single {@link NDArray}.
     *
     * @param manager the {@link NDManager} to create the new NDArray in
     * @param shape the {@link Shape} for the new NDArray
     * @param dataType the {@link DataType} for the new NDArray
     * @return Returns the NDArray initialized with the manager and shape
     */
    NDArray initialize(NDManager manager, Shape shape, DataType dataType);
}