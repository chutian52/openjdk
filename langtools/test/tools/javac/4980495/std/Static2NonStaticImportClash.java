/*
 * @test  /nodynamiccopyright/
 * @bug 7101822
 * @summary Check the when clashing types are imported through an ordinary and static import,
 *          the compile-time error is properly reported.
 * @compile/fail/ref=Static2NonStaticImportClash.out -XDrawDiagnostics Static2NonStaticImportClash.java p1/A1.java p2/A2.java
 *
 */

import static p2.A2.f;
import p1.A1.f;

public class Static2NonStaticImportClash {
}
