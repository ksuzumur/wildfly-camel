/*
 * #%L
 * Wildfly Camel :: Testsuite
 * %%
 * Copyright (C) 2013 - 2014 RedHat
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

package org.wildfly.camel.test.classloading;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.modules.ModuleClassLoader;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
@Ignore("[ARQ-1913] ARQ calls test methods with TCCL being set")
public class ThreadContextClassloaderTest {

    @Deployment
    public static JavaArchive deployment() {
        final JavaArchive archive = ShrinkWrap.create(JavaArchive.class, "tccl-tests");
        return archive;
    }

    @BeforeClass
    public static void beforeClass() throws Exception {
        assertNoTCCL();
    }

    @AfterClass
    public static void afterClass() throws Exception {
        assertNoTCCL();
    }

    @Before
    public void before() throws Exception {
        assertNoTCCL();
    }

    @After
    public void after() throws Exception {
        assertNoTCCL();
    }

    @Test
    public void testClassLoader() throws Exception {
        assertNoTCCL();
    }

    private static void assertNoTCCL() {
        ClassLoader tccl = Thread.currentThread().getContextClassLoader();
        if (tccl instanceof ModuleClassLoader) {
            Assert.assertNull("TCCL is null", tccl);
        }
    }
}
