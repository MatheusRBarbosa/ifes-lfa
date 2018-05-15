(mealy
(symbols-in a b)
(symbols-out 0 1)
(states q0 q1 q2)
(start q0)
(finals q2)
(trans
(q0 q1 a 0) (q0 q1 b 0) (q1 q2 b 0) (q1 q2 a 0)
(q2 q2 a 1) (q2 q2 b 1)))
