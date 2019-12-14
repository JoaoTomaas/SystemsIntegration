        //14. Windowed -> Total de revenues na última hora (Está para 60 segundos) receitas
        KTable<Windowed<String>, Double> window_rev_total = stream_cliente.mapValues(v -> transformValue(v)).groupBy((key, value) -> "total", Grouped.with(Serdes.String(), Serdes.Double())).windowedBy(TimeWindows.of(TimeUnit.MINUTES.toMillis(1))).reduce((v1, v2) -> v1 + v2);
        //window_rev_total.toStream((wk, v) -> wk.key()).map((k, v) -> new KeyValue<>(k, "" + k + "-->" + v)).to(output_topic, Produced.with(Serdes.String(), Serdes.String()));
        window_rev_total.toStream((wk, v) -> wk.key()).map((k, v) -> new KeyValue<>(k, "" + k + "-->" + v)).foreach((key, value) -> System.out.println("Window rev:" + key + "  " + value));

        //15. Windowed -> Total de expenses na última hora (gasros)
        KTable<Windowed<String>, Double> window_exp_total = stream_fornece.mapValues(v -> transformValue(v)).groupBy((key, value) -> "total", Grouped.with(Serdes.String(), Serdes.Double())).windowedBy(TimeWindows.of(TimeUnit.MINUTES.toMillis(1))).reduce((v1, v2) -> v1 + v2);
        //window_exp_total.toStream((wk, v) -> wk.key()).map((k, v) -> new KeyValue<>(k, "" + k + "-->" + v)).to(output_topic, Produced.with(Serdes.String(), Serdes.String()));
        window_exp_total.toStream((wk, v) -> wk.key()).map((k, v) -> new KeyValue<>(k, "" + k + "-->" + v)).foreach((key, value) -> System.out.println("Windows exp : " + key + "  " + value));

        //16. Windowed -> Total profit na última hora (FALTAAAAAAAAAAAA TESTAAAAAAAAAAAAAAAR)
            //Primeiro tenho que fazer um join com as duas windows do revenue e das expenses




        KTable<Windowed<String>, Double> join_window1 = window_rev_total.join(window_exp_total,
                (leftValue, rightValue) -> leftValue-rightValue // ValueJoiner
        );
        join_window1.toStream((wk, v) -> wk.key()).map((k, v) -> new KeyValue<>(k, "" + v)).foreach((key, value) -> System.out.println("Windows prof: " + value));
