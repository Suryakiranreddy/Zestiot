Set oShell = CreateObject ("Wscript.Shell") 
Dim strArgs
strArgs = "cmd /c D:\ProjectsNew\ZestIOT\Run.bat"
oShell.Run strArgs, 0, false