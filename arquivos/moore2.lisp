(moore
(symbols-in a b)
(symbols-out 0 1)
(states q0 q1 q2)
(start q0)
(finals q2)
(trans
(q0 q1 a) (q0 q1 b) (q1 q0 a) (q1 q2 b)
(q2 q2 a) (q2 q2 b))
(out-fn
(q0 0) (q1 1) (q2 0)))