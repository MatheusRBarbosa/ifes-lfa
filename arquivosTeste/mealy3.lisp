(mealy
(symbols-in a b)
(symbols-out 0 1)
(states q0 q1 q2 q3)
(start q0)
(finals q3)
(trans
(q0 q1 a 0) (q0 q3 b 0) (q1 q2 b 1) (q1 q3 a 1)
(q2 q3 a 0) (q2 q3 b 1) (q3 q0 b 1) (q3 q3 a 1)))