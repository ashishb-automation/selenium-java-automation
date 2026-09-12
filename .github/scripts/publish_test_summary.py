from __future__ import annotations

import glob
import os
import sys
import xml.etree.ElementTree as ET
from pathlib import Path

reports = glob.glob("target/surefire-reports/TEST-*.xml")
totals = {"tests": 0, "failures": 0, "errors": 0, "skipped": 0, "time": 0.0}

for report in reports:
    root = ET.parse(report).getroot()
    totals["tests"] += int(root.attrib.get("tests", 0))
    totals["failures"] += int(root.attrib.get("failures", 0))
    totals["errors"] += int(root.attrib.get("errors", 0))
    totals["skipped"] += int(root.attrib.get("skipped", 0))
    totals["time"] += float(root.attrib.get("time", 0.0))

failed = totals["failures"] + totals["errors"]
passed = totals["tests"] - failed - totals["skipped"]
browser = os.getenv("BROWSER", "browser").title()
icon = "✅" if reports and failed == 0 else "❌"
headline = (
    f"{icon} {passed} passed, {failed} failed, {totals['skipped']} skipped"
    if reports
    else "❌ Surefire reports were not generated"
)

summary = f"""## {browser} test results

### {headline}

| Total | Passed | Failed | Skipped | Duration |
| ---: | ---: | ---: | ---: | ---: |
| {totals['tests']} | {passed} | {failed} | {totals['skipped']} | {totals['time']:.2f}s |

Cucumber and JUnit reports are available in the workflow artifacts.
"""

print(headline)
if path := os.getenv("GITHUB_STEP_SUMMARY"):
    with Path(path).open("a", encoding="utf-8") as output:
        output.write(summary)

if path := os.getenv("GITHUB_OUTPUT"):
    with Path(path).open("a", encoding="utf-8") as output:
        output.write(f"passed={passed}\nfailed={failed}\nskipped={totals['skipped']}\n")

if not reports:
    print("::error title=Test reports missing::Maven Surefire did not create TEST-*.xml files")
    sys.exit(1)

if failed:
    print(f"::error title={browser} tests failed::{failed} failed, {passed} passed")
else:
    print(f"::notice title={browser} tests passed::{passed} passed, {totals['skipped']} skipped")
