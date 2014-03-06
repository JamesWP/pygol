pygol
=====

Python implementation of conways game of life
 - James Peach

Usage
=====
provide game of life file to stdin (see glidercreator example)

receive next itteration on stdout

example:

```
 $ cat glidercreator | python g.py
```

or to see animation

```
 $ while [ true ]; do
   cp glidercreator tmp
   cat tmp | python p.py > tmp2
   mv tmp2 tmp
 done
```
