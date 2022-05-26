HULKMAP is an open-source online local vulnerability scanner project. It consists of online local vulnerability scanning programs for Windows and Linux operating systems. These scripts can be used for defensive and offensive purposes. It is possible to make vulnerability assessments using these scripts. Also, they can be used for privilege escalation by pentesters/red teamers.

HULKMAP can be used to, scan vulnerabilities on the localhost, see related exploits, and download them. Scripts basically, scan the localhost to gather installed software information and ask vulmon.com API if there are any vulnerabilities and exploits related to installed software. If vulnerabilities exist, HULKMAP gives CVE ID, risk score, vulnerability's detail link, if exists related exploit ids, and exploit titles. Exploits can be downloaded with HULKMAP also.

**The main idea of HULKMAP is getting real-time vulnerability data from Vulmon instead of relying on a local vulnerability database. Even the most recent vulnerabilities can be detected with this approach.** Also, its exploit download feature aids privilege escalation processes. Pentesters and red teamers can download exploits from Exploit DB from the command prompt. To use this feature only thing needed is the id of exploits.

Since most Linux installations have Python, HULKMAP Linux is developed with Python while HULKMAP Windows is developed with PowerShell to make it easy to run it on most Windows versions. HULKMAP Linux is compatible with Python 2.x, 3.x, and dpkg package management system. HULKMAP Windows is compatible with PowerShell v3 and higher.

## To-Do:
* Operating system level vulnerabilities will be detected at Windows
* Other Linux package management systems will be supported
* macOS script will be developed
* Android and iOS scripts will be developed

## Main Contributors
* [Yavuz Atlas](https://github.com/yavuzatlas)
* [Fatih Özel](https://github.com/ozelfatih)
* [Hakan Bayır](https://github.com/HakanBayir)