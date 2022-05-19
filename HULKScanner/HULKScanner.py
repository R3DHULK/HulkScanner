import socket
from sys import modules
import time
import threading
from queue import Queue

# pip install modules

# Don't dare to copy my code, I'll eat your cookies
# else you build yourself or copy others

logo = '''
      _   _ _   _ _     _   __  _____                                 
     | | | | | | | |   | | / / /  ___|                                
     | |_| | | | | |   | |/ /  \ `--.  ___ __ _ _ __  _ __   ___ _ __ 
     |  _  | | | | |   |    \   `--. \/ __/ _` | '_ \| '_ \ / _ \ '__|
     | | | | |_| | |___| |\  \ /\__/ / (_| (_| | | | | | | |  __/ |   
     \_| |_/\___/\_____|_| \_/ \____/ \___\__,_|_| |_|_| |_|\___|_|   
                                                                 
               coded by Sumalya Chatterjee
'''
print(logo)
socket.setdefaulttimeout(0.25)
lock = threading.Lock()

ip_address = input('IP Address or Website URL : ')
host = socket.gethostbyname(ip_address)
print ('Scanning on IP Address: ', host)

def scan(port):
   sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
   try:
      con = sock.connect((host, port))
      with lock:
         print(port, 'is open')
      con.close()
   except:
      pass

def execute():
   while True:
      worker = queue.get()
      scan(worker)
      queue.task_done()
      
queue = Queue()
start_time = time.time()
   
for x in range(100):
   thread = threading.Thread(target = execute)
   thread.daemon = True
   thread.start()
   
for worker in range(1, 500):
   queue.put(worker)
   
queue.join()

print('Time taken:', time.time() - start_time)
print ("Thank You for using my tool :>")