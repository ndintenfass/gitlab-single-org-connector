# CircleCI GitLab Connector Demonstration

This project demonstrates the concept of a CircleCI Connector by connecting a
single org on GitLab.com to CircleCI.com

**This software is experimental and for demonstration purposes only. It is
provided as-is and with no warranty or other representation of production
quality.**

## Logging

By default, this service will log to standard output. To configure it further,
please refer to the [Dropwizard logging configuration
documentation](https://www.dropwizard.io/0.8.0/docs/manual/core.html#logging).

## StatsD Metrics

By default this service exposes metrics at `/metrics` on port 8081. If you
need the service to emit statsd metrics you can add some or all of the
following to the config file for the service.

```yaml
statsd:
  host: statsd.example.com
  port: 8125
  refreshPeriodSeconds: 10
```

The only mandatory key is `host`. The others are set to their default values
above.
