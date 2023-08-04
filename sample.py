### [0 Initializing global variables & creating format for CAD Record text file --
from datetime import date
from datetime import datetime

import os.path
import socket   
import os
import pyautogui, sys
import time

today = date.today()
now = datetime.now()
current_time = now.strftime("%H:%M:%S")

### --- Set Initial Mouse Status
Mstatus = "Program Start"

a = int(0)
b = int(0)
#x1 = int(0)
#y1 = int(0)



str1="CAD_record"
date_today=str(today)

## Combine file name and date - for checking (not working)
CAD1str=str1+"["+date_today+"]"+".txt"



### --- Create the text file -----------------------------------------

file_exists = os.path.exists(CAD1str)

if file_exists == True:
    print("File is present")
elif file_exists == False:
    print("Creating new file")
    f = open(CAD1str, 'x')
    f.close()
### -----------------------------------------------------------------

### ---- Gather important information -and record to text file ----

# from datetime import date
user = os.getlogin()
hostname = socket.gethostname()
IPAddr=socket.gethostbyname(hostname)

os.system('cmd /c tasklist > runtask.txt') 

### --------------------------------------------------------------

### --- Monitor mouse active movement --------------------------
mouseStat = 'N/A'

### ----  Compare to new position
def MouseChk():
    
    x1, y1 = pyautogui.position()
    print("=====mouse check=======")
    print(a)
    print(b)
    print("Compare above and below")
    print(x1)
    print(y1)
    print("============")
    
    if a!=x1 or b!= y1:
        mouseStat = 'Active'
#        print(mouseStat)
        return str(mouseStat)       
    elif a==x1 and b==y1: 
        mouseStat = 'Idle'
#        print(mouseStat)
        return str(mouseStat)

### ---- Get the current mouse position    
def MousePos():
    x1, y1 = pyautogui.position()
    a = int(x1)
    b = int(y1)
    return a ,b

### -----------------------Mouse--------------------------------------

print("get initial mouse pos")
a, b = MousePos()
print(a)
print(b)
print("+++++++")
      
### ------------------Start 5 min  Timer ----------------------------
def countdown(t):
   while t:
        mins, secs = divmod(t, 60)
        timer = '{:02d}:{:02d}'.format(mins, secs)
#        print(timer, "\r")
        window.update_idletasks()
        update_display()
        lbl=Label(window, text="timer", fg='blue', font=("Helvetica", 12))
        lbl.place(x=150, y=90)
        time.sleep(1)
        t -= 1
### ------------------- Timer ---------------------------------------


### [ 3 Check for running applications ------------------------------
def AppCheck():
    global ansys
    global cadra
    global catia
    global sldworks
    
    CAD_list = open('runtask.txt')

    ## **Note: Case sensitive
    search_words=['ANSYS.exe', 'cadra.exe', 'CATSTART.exe', 'SLDWORKS.exe']

    ansys = 0
    cadra = 0
    catia = 0
    sldworks = 0

    for line in CAD_list:
        if any(word in line for word in search_words):
    ## Code to identify founds words         
            if 'ANSYS.exe' in line:
                ansys = ansys + 1       
            elif 'cadra.exe' in line:
                cadra = cadra + 1
            elif 'CATSTART.exe' in line:
                catia = catia + 1
            elif 'SLDWORKS.exe' in line:
                sldworks = sldworks + 1

    return ansys, cadra, catia, sldworks

    CAD_list.close()
### -------------------------------------------------------------- 3 ]



### [ 4 Save all information to CAD_record -------------------------
def SaveRecord():
#    file1 = open('userinfo.txt', 'r')
    file2 = open(CAD1str, 'a+', encoding='utf-8')
    
    file2.write(user)
    file2.write("\n")
    file2.write(hostname)
    file2.write("\n")
    file2.write(IPAddr)
    file2.write('\n')
    file2.write(date_today)
    file2.write('\n')
    file2.write(current_time)
    file2.write('\n')
    file2.write('Mouse Status::')
    file2.write(Mstatus)
    file2.write('\n')

    if ansys > 0:
        print('Found Ansys::', ansys)
        output1 = "Found Ansys::" + str(ansys) + ("\n")
        file2.write(output1)
    if cadra > 0:
        print('Found Cadra::', cadra)
        output1 = "Found Cadra::" + str(cadra) + ("\n")
        file2.write(output1)
    if catia > 0:
        print('Found Catia::', catia)
        output1 = "Found Catia::" + str(catia) + ("\n")
        file2.write(output1)
    if sldworks > 0:    
        print('Found Dll::', sldworks)
        output1 = "Found Dll::" + str(sldworks) + ("\n")
        file2.write(output1)
    
    file2.write('--------------------------\n') 
#    file1.close()
    file2.close()
    
#### ----------------------------------- 4 ]

#### Connect to MySQL and save record ------------------------
def Save2DB():
    import mysql.connector

    cnx = mysql.connector.connect(user='sums_user', password='!@#SUMs123', host='10.49.10.234', database='sums')
    mycursor = cnx.cursor()

    sql = "INSERT INTO sums_record (petid, hostname, ip_addr, sums_date, sums_time, mouse_stat, cad1, cad2, cad3, cad4) VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s)"
    val = user, hostname, IPAddr, date_today, current_time, Mstatus, ansys, cadra, catia, sldworks  

    mycursor.execute(sql, val)

    cnx.commit()
    cnx.close()

    print(mycursor.rowcount, "record inserted.")

### -- Windows Gui Text --------------------------------------------------------
def showDisplay():
    lbl=Label(window, text="Current Date::", fg='red', font=("Helvetica", 12))
    lbl.place(x=5, y=0)
    lbl=Label(window, text="Record Time::", fg='red', font=("Helvetica", 12))
    lbl.place(x=5, y=30)
    lbl=Label(window, text="Mouse Movement::", fg='red', font=("Helvetica", 12))
    lbl.place(x=5, y=60)
    lbl=Label(window, text="Timer [5min]::", fg='red', font=("Helvetica", 12))
    lbl.place(x=5, y=90)
### ----------------------------------------------------------------------------

### -- Show Values in GUI text -------------------------------------------------
def showValues():
    from datetime import datetime
    
    lbl=Label(window, text=date_today, fg='blue', font=("Helvetica", 12))
    lbl.place(x=110, y=0)

    now = datetime.now()
    current_time = now.strftime("%H:%M:%S")

    lbl=Label(window, text=current_time, fg='blue', font=("Helvetica", 12))
    lbl.place(x=110, y=30)

    lbl=Label(window, text=Mstatus , fg='blue', font=("Helvetica", 12))
    lbl.place(x=150, y=60)
### ----------------------------------------------------------------------------

### -- Countdown Timer ---------------------------------------------------------
def countdown(t):
    import time
    while t:
        mins, secs = divmod(t, 60)
        timer = '{:02d}:{:02d}'.format(mins, secs)
        window.update()
        window.update_idletasks()
### --- Display countdown timer
        lbl=Label(window, text=timer, fg='blue', font=("Helvetica", 12))
        lbl.place(x=110, y=90)

        time.sleep(1)
        t -= 1
### ------------------------------------------------------------------------


### Create Window Here --------------------------------------------
from tkinter import *
from pystray import MenuItem as item

import pystray
from PIL import Image, ImageTk

window=Tk()

window.geometry("300x200")    
window.title('SUMs Monitoring Windows')

### --- Show EXIT Button -----------------------------
exit_btn=Button(window, text="Exit!!", fg='blue', command=window.destroy)
exit_btn.place(x=250, y=150)
### --------------------------------------------------

# Define a function for quit the window
def quit_window(icon, item):
    icon.stop()
    window.destroy()
    
# Define a function to show the window again
def show_window(icon, item):
    icon.stop()
    window.after(0,window.deiconify())    
    
# Hide the window and show on the system taskbar
def hide_window():
    window.withdraw()
    image=Image.open("favicon.ico")
    menu=(item('Quit', quit_window), item('Show', show_window))
    icon=pystray.Icon("name", image, "My System Tray Icon", menu)
    icon.run()


window.protocol('WM_DELETE_WINDOW', hide_window)

showDisplay()
showValues()

## Infinite loop
condition=True
def infinite_loop():
        global a
        global b
        global Mstatus

###     Record the initial mouse position
        a, b = MousePos()
###     countdown 5 min (300)
        countdown(10)
###     check and compare the initial vs new
        Mstatus = MouseChk()
        window.update()
        
        showValues()
        lbl=Label(window, text="--------------------", fg='blue', font=("Helvetica", 12))
        lbl.place(x=150, y=60)
        lbl=Label(window, text=Mstatus, fg='blue', font=("Helvetica", 12))
        lbl.place(x=170, y=60)
###     Save the New position
        a, b = MousePos()
        print("+++++ MousePos +++++++")
        print("Save the new position")
        print(a)
        print(b)
        print("++++++++++")
        
###     Check for Running Apps
        ansys, cadra, catia, sldworks = AppCheck()

###     Save Record
        SaveRecord()
###     Save to Database
#        Save2DB()

        now = datetime.now()
        current_time = now.strftime("%H:%M:%S")
     
        lbl=Label(window, text=current_time, fg='blue', font=("Helvetica", 12))
        lbl.place(x=110, y=30)

        window.update()
        window.update_idletasks()
        infinite_loop()

#stop = Button(win, text="Stop the Loop", font="Arial, 12", command=stop).pack()

infinite_loop()
window.mainloop()

#txtfld=Entry(window, text="This is Entry Widget", bd=5)
#txtfld.place(x=80, y=150)

# except KeyboardInterrupt:
#        print('\n')

### END LOOP ------------------------------------------------------

