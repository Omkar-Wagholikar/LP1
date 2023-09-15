import math
def getSubnet(vals):
    prime = vals[0]
    subnet = []
    if prime <128:
        #class a
        subnet = 24
    elif prime <191:
        #class b
        subnet = 16
    elif prime <233:
        #class c
        subnet = 8
    elif prime <239:
        #class d
        subnet = -12
    elif prime <255:
        #class e
        subnet = -12
    return subnet

ip = "192.168.1.0"
num = 100

# ip = input("IP: ")
# num = int(input("Num:"))
vals = list(map(int, ip.split(".")))
subnet = getSubnet(vals)
bits = math.ceil(math.log2(num))
h = subnet - bits
n = bits
print("H:", h, "n:", n)
print(2 ** h - 2,2 ** n)
