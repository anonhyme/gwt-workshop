/****************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one   *
 * or more contributor license agreements.  See the NOTICE file *
 * distributed with this work for additional information        *
 * regarding copyright ownership.  The ASF licenses this file   *
 * to you under the Apache License, Version 2.0 (the            *
 * "License"); you may not use this file except in compliance   *
 * with the License.  You may obtain a copy of the License at   *
 *                                                              *
 *   http://www.apache.org/licenses/LICENSE-2.0                 *
 *                                                              *
 * Unless required by applicable law or agreed to in writing,   *
 * software distributed under the License is distributed on an  *
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY       *
 * KIND, either express or implied.  See the License for the    *
 * specific language governing permissions and limitations      *
 * under the License.                                           *
 ****************************************************************/
package gwtws.mvp.client;

import gwtws.mvp.client.guice.MyTestClientModule;
import gwtws.mvp.server.guice.MyServerModule;

import javax.servlet.http.HttpSession;

import junit.framework.TestCase;
import net.customware.gwt.presenter.client.EventBus;

import com.google.gwt.junit.GWTMockUtilities;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

/**
 * Base class for testing presenters.
 * Tests extending this class only work in jvm.
 * 
 * @author manolo
 *
 */
public abstract class HupaMvpTestCase extends TestCase {
    
    protected Injector injector = Guice.createInjector(getModules());

    protected HttpSession httpSession;
    protected EventBus eventBus;
    
    protected Module[] getModules() {
        return new Module[]{new MyServerModule(), new MyTestClientModule()};
    }

    @Override
    protected void setUp() throws Exception {
        try {
            GWTMockUtilities.disarm();
            httpSession = injector.getInstance(HttpSession.class);
            eventBus = injector.getInstance(EventBus.class);
        } catch (Exception e) {
        }
    }
}
