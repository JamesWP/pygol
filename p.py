from itertools import permutations
import sys

def main():
  try:
    b = loadBoard()
    b = iterateBoard(b)
    printBoard(b)
  except KeyboardInterrupt:
    raise SystemExit

def loadBoard():
   board = []
   for line in sys.stdin:
     board.append(map(untile,line.split(' ')))
   return board
def iterateBoard(b):
  b2 = createBoard(len(b))
  for nu in xrange(len(b)*len(b)):
    x = nu%len(b)
    y = nu/len(b)
    b2[x][y] = iterateCell(b,x,y)
  return b2

def iterateCell(b,cx,cy):
  n = neigbours(b,cx,cy)
  if(b[cx][cy]==1):
    if n==2 or n==3: return 1
  else:
    if n==3: return 1
  return 0

def neigbours(b,cx,cy):
  n=0
  s=len(b)
  if(cx+1<s and            b[cx+1][cy  ]==1): n=n+1;
  if(cx+1<s and cy+1<s and b[cx+1][cy+1]==1): n=n+1;
  if(cx+1<s and cy>0 and   b[cx+1][cy-1]==1): n=n+1;
  if(cy+1<s and            b[cx  ][cy+1]==1): n=n+1;
  if(cy>0 and              b[cx  ][cy-1]==1): n=n+1;
  if(cx>0 and              b[cx-1][cy  ]==1): n=n+1;
  if(cx>0 and cy+1<s and   b[cx-1][cy+1]==1): n=n+1;
  if(b[cx-1][cy-1]==1): n=n+1; 
  return n

def createBoard(size):
  return map(
          lambda x: 
            map(lambda y:0,xrange(size))
            ,xrange(size)
        )

def printBoard(b):
  print '\n'.join(map(lambda x:' '.join(map(tile,x)),b))

def tile(x):
  return '#' if x==1 else '-'

def untile(x):
  return 1 if x=='#' else 0 

main()
