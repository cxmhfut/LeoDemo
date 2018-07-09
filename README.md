# LeoDemo

App for Leo robot about dialogue system

## Leo 机器人App安装到机器人内部步骤

- 1.要求第三方程序要有Root权限才能使用，所以开发好的程序需要push到/system/app中才能正常使用。
连接好机器人，打开命令窗口，输入adb shell，如果前面操作都没有问题的话应该出现如下提示：

![leo_demo_01](https://github.com/cxmhfut/LeoDemo/blob/master/images/leo_demo_01.png)

- 2.后面的“$”符说明现在是release模式，没有push文件的权限，通过如下命令可进入eng模式，获取push文件的权限：

```
ps adbd
kill -12 [上一行显示的pid号]
adb remount
```

![leo_demo_02](https://github.com/cxmhfut/LeoDemo/blob/master/images/leo_demo_02.png)

- 3.如上图所示，经过以上步骤，在输入adb shell进入android文件系统时，提示符已经变成了“#”，说明进入eng模式，可以
push文件了。再用如下命令，就可以删除原来的机器人应用，并将你的应用push到机器人中：

```
rm system/app/SpeechMainActivity.apk
exit
adb push [你的apk路径] system/app
```

- SpeechMainActivity.apk就是机器人原来的apk，在删除之前请用adb pull system/app/SpeechMainActivity.apk进行备份，
可以用来恢复机器人的功能。
